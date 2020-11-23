package nl.hannahsten.texifyidea.lang

/**
 * @author Hannah Schellekens
 */
interface Dependend {

    /**
     * Get the package that is required for the object to work.
     *
     * @return The package object, or [LatexPackage.DEFAULT] when no package is needed.
     */
    val dependency: LatexPackage
}
