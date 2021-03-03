package nl.hannahsten.texifyidea.settings.sdk

import com.intellij.openapi.project.Project
import com.intellij.openapi.projectRoots.ProjectJdkTable
import com.intellij.openapi.projectRoots.Sdk
import com.intellij.openapi.roots.OrderRootType
import com.intellij.openapi.roots.ProjectRootManager
import com.intellij.openapi.util.SystemInfo
import com.intellij.openapi.vfs.VirtualFile
import nl.hannahsten.texifyidea.run.latex.LatexDistributionType
import nl.hannahsten.texifyidea.util.runCommand
import java.io.File

/**
 * Utility functions which are not specific to a [LatexSdk] or a [nl.hannahsten.texifyidea.run.compiler.LatexCompiler].
 */
object LatexSdkUtil {

    val isPdflatexInPath: Boolean by lazy {
        "pdflatex --version".runCommand()?.contains("pdfTeX") == true
    }

    val pdflatexVersionText: String by lazy {
        getDistribution()
    }

    /**
     * Whether the user is using MikTeX or not.
     * This value is lazy, so only computed when first accessed, because it is unlikely that the user will change LaTeX distribution while using IntelliJ.
     */
    val isMiktexAvailable: Boolean by lazy {
        pdflatexVersionText.contains("MiKTeX")
    }

    private val isWslTexliveAvailable: Boolean by lazy {
        SystemInfo.isWindows && runCommand("bash", "-ic", "pdflatex --version")?.contains("pdfTeX") == true
    }

    val isTlmgrInstalled: Boolean by lazy {
        "tlmgr --version".runCommand()?.contains("TeX Live") == true
    }

    // Cache for below function
    private var isTlgmrAvailable: Boolean? = null

    /**
     * Contrary to e.g. with pdflatex, we want to fail silently if tlmgr is not available.
     * Therefore we check if it really is available after getting the executable name/path.
     */
    fun isTlmgrAvailable(project: Project): Boolean {
        if (isTlmgrInstalled) {
            isTlgmrAvailable = true
            return true
        }
        isTlgmrAvailable?.let { return it }
        // If not set, find out if it is available
        val tlmgrExecutable = getExecutableName("tlmgr", project)
        isTlgmrAvailable = "$tlmgrExecutable --version".runCommand()?.contains("TeX Live") == true
        return isTlgmrAvailable!!
    }

    /**
     * Whether the user does not have MiKTeX or TeX Live, but does have the miktex docker image available.
     * In this case we assume the user wants to use Dockerized MiKTeX.
     */
    private fun defaultIsDockerMiktex() =
        (!isMiktexAvailable && !TexliveSdk.isAvailable && DockerSdk.isAvailable)

    fun isAvailable(type: LatexDistributionType, project: Project): Boolean {
        if (type == LatexDistributionType.PROJECT_SDK && getLatexProjectSdk(project) != null) return true
        if (type == LatexDistributionType.MIKTEX && isMiktexAvailable) return true
        if (type == LatexDistributionType.TEXLIVE && TexliveSdk.isAvailable) return true
        if (type == LatexDistributionType.DOCKER_MIKTEX && DockerSdk.isAvailable) return true
        if (type == LatexDistributionType.WSL_TEXLIVE && isWslTexliveAvailable) return true
        return false
    }

    /**
     * Given the path to the LaTeX home, find the parent path of the executables, e.g. /bin/x86_64-linux/
     */
    fun getPdflatexParentPath(homePath: String) = File("$homePath/bin").listFiles()?.firstOrNull()?.path

    /**
     * Find the full name of the distribution in use, e.g. TeX Live 2019.
     */
    private fun getDistribution(): String {
        // Could be improved by using the (project-level) LaTeX SDK if pdflatex is not in PATH
        return parsePdflatexOutput(runCommand("pdflatex", "--version") ?: "")
    }

    /**
     * Parse the output of pdflatex --version and return the distribution.
     * Assumes the distribution name is in brackets at the end of the first line.
     */
    fun parsePdflatexOutput(output: String): String {
        val firstLine = output.split("\n")[0]
        val splitLine = firstLine.split("(", ")")

        // Get one-to-last entry, as the last one will be empty after the closing )
        return if (splitLine.size >= 2) {
            splitLine[splitLine.size - 2]
        }
        else {
            ""
        }
    }

    /**
     * Get default LaTeX distribution type (for the run configuration).
     */
    fun getDefaultLatexDistributionType(project: Project): LatexDistributionType {
        return when {
            getLatexProjectSdk(project) != null -> LatexDistributionType.PROJECT_SDK
            isMiktexAvailable -> LatexDistributionType.MIKTEX
            TexliveSdk.isAvailable -> LatexDistributionType.TEXLIVE
            defaultIsDockerMiktex() -> LatexDistributionType.DOCKER_MIKTEX
            else -> LatexDistributionType.TEXLIVE
        }
    }

    /**
     * Get executable name of pdflatex, which in case it is not in PATH may be prefixed by the full path (or even by a docker command).
     */
    fun getExecutableName(executableName: String, project: Project): String {
        // Give preference to the project SDK if a valid LaTeX SDK is selected
        getLatexProjectSdk(project)?.let { sdk ->
            if (sdk.homePath != null) {
                (sdk.sdkType as? LatexSdk)?.getExecutableName(executableName, sdk.homePath!!)?.let { return it }
            }
        }
        // If not, if it's in path then that also works
        if (isPdflatexInPath) {
            return executableName
        }
        // If it's also not it path, just try a few sdk types with the default home path
        val preferredSdk = getPreferredSdkType()?.sdkType as? LatexSdk ?: return executableName
        return preferredSdk.suggestHomePath()?.let { preferredSdk.getExecutableName(executableName, it) } ?: executableName
    }

    /**
     * Assuming the goal is to be able to execute e.g. pdflatex, try to find a suitable LaTeX distribution to find a command to use pdflatex.
     * Check all available Project SDKs and return a good one (arbitrary order of preference).
     */
    private fun getPreferredSdkType(): Sdk? {
        val allSdks = ProjectJdkTable.getInstance().allJdks.filter { it.sdkType is LatexSdk }
        allSdks.firstOrNull { it.sdkType is TexliveSdk }?.let { return it }
        allSdks.firstOrNull { it.sdkType is MiktexWindowsSdk }?.let { return it }
        allSdks.firstOrNull { it.sdkType is DockerSdk }?.let { return it }
        return null
    }

    /**
     * If a LaTeX SDK is selected as project SDK, return it, otherwise return null.
     */
    fun getLatexProjectSdk(project: Project): Sdk? {
        val sdk = ProjectRootManager.getInstance(project).projectSdk
        if (sdk?.sdkType is LatexSdk) {
            return sdk
        }
        return null
    }

    /**
     * Get type of project SDK. If null or not a LaTeX sdk, return null.
     */
    fun getLatexProjectSdkType(project: Project): LatexSdk? {
        return getLatexProjectSdk(project)?.sdkType as? LatexSdk
    }

    /**
     * Similar to [getSdkSourceRoots] but for package style files.
     * Only works when a LaTeX project sdk is selected.
     */
    fun getSdkStyleFileRoots(project: Project): Set<VirtualFile> {
        getLatexProjectSdk(project)?.let { sdk ->
            return if (sdk.homePath != null) setOf((sdk.sdkType as? LatexSdk)?.getDefaultStyleFilesPath(sdk.homePath!!)).filterNotNull().toSet() else emptySet()
        }
        return emptySet()
    }

    /**
     * Collect SDK source paths, so paths to texmf-dist/source/latex, based on Project SDK if available (combining the default
     * for the SDK type and any user-added source roots) and otherwise on a random guess (ok not really).
     */
    fun getSdkSourceRoots(project: Project): Set<VirtualFile> {
        // Get user provided and default source roots
        getLatexProjectSdk(project)?.let { sdk ->
            val userProvided = sdk.rootProvider.getFiles(OrderRootType.SOURCES).toSet()
            val default = if (sdk.homePath != null) setOf((sdk.sdkType as? LatexSdk)?.getDefaultSourcesPath(sdk.homePath!!)).filterNotNull() else emptySet()
            return userProvided + default
        }

        // If no sdk is known, guess something
        for (sdkType in setOf(TexliveSdk(), NativeTexliveSdk(), MiktexWindowsSdk())) {
            val roots = sdkType.suggestHomePaths().mapNotNull { homePath ->
                sdkType.getDefaultSourcesPath(homePath)
            }.toSet()
            if (roots.isNotEmpty()) return roots
        }
        return emptySet()
    }
}