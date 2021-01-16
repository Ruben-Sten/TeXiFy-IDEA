package nl.hannahsten.texifyidea.ui.symbols

import nl.hannahsten.texifyidea.lang.LatexCommand
import nl.hannahsten.texifyidea.lang.LatexMathCommand.*
import nl.hannahsten.texifyidea.lang.LatexRegularCommand

/**
 * @author Hannah Schellekens
 */
object SymbolCategories {

    /**
     * Maps each category to the symbols that are in the category.
     *
     * The categories are ordered, as are the symbols per category.
     */
    val categories: Map<SymbolCategory, List<SymbolUiEntry>> = LinkedHashMap<SymbolCategory, List<SymbolUiEntry>>().apply {

        createCategory("Operators") {
            add(DryUiEntry(description = "plus sign", "+", "misc_plus.png", "+", true))
            add(DryUiEntry(description = "minus sign", "-", "misc_minus.png", "-", true))
            add(PLUS_MINUS)
            add(MINUS_PLUS)
            add(TIMES)
            add(DIV)
            add(ASTERISK)
            add(STAR)
            add(CIRCLE)
            add(BULLET)
            add(DIVIDEONTIMES)
            add(LTIMES)
            add(RTIMES)
            add(CDOT)
            add(DOT_PLUS)
            add(LEFT_THREE_TIMES)
            add(RIGHT_THREE_TIMES)
            add(DryUiEntry(description = "factorial", "!", "misc_factorial.png", "!", true))
            add(AMALGAMATION)
            add(CIRCLED_TIMES)
            add(CIRCLED_PLUS)
            add(CIRCLED_MINUS)
            add(CIRCLED_SLASH)
            add(CIRCLED_DOT)
            add(CIRCLED_CIRCLE)
            add(CIRCLED_DASH)
            add(CIRCLED_ASTERISK)
            add(BIG_CIRCLE)
            add(BOXED_DOT)
            add(BOXED_MINUS)
            add(BOXED_PLUS)
            add(BOXED_TIMES)
            add(DIAMOND)
            add(BIG_TRIANGLE_UP)
            add(TRIANGLE_LEFT)
            add(TRIANGLE_RIGHT)
            add(LHD)
            add(RHD)
            add(UN_LHD)
            add(UN_RHD)
            add(UNION)
            add(INTERSECTION)
            add(MULTISET_UNION)
            add(DOUBLE_UNION)
            add(DOUBLE_INTERSECTION)
            add(WREATH_PRODUCT)
            add(SET_MINUS)
            add(SMALL_SET_MINUS)
            add(SQUARE_CAP)
            add(SQUARE_CUP)
            add(NOT_SIGN)
            add(INVERSED_NOT_SIGN)
            add(WEDGE)
            add(VEE)
            add(WEDGE_BAR)
            add(VEE_BAR)
            add(DOUBLE_BAR_WEDGE)
            add(CURLY_WEDGE)
            add(CURLY_VEE)
            add(DAGGER)
            add(DOUBLE_DAGGER)
            add(INTERCALATE)
            add(N_ARY_INTERSECTION)
            add(N_ARY_UNION)
            add(N_ARY_UNION_WITH_PLUS)
            add(N_ARY_SQUARE_UNION)
            add(N_ARY_PRODUCT)
            add(N_ARY_COPRODUCT)
            add(BIG_WEDGE)
            add(BIG_VEE)
            add(BIG_CIRCLED_DOT)
            add(BIG_CIRCLED_PLUS)
            add(BIG_CIRCLED_TIMES)
            add(SUM)
            add(INTEGRAL)
            add(CONTOUR_INTEGRAL)
            add(DOUBLE_INTEGRAL)
            add(TRIPLE_INTEGRAL)
            add(QUADRUPLE_INTEGRAL)
            add(DOTS_INTEGRAL)
        }

        createCategory("Math functions") {
            add(INVERSE_COSINE)
            add(INVERSE_SINE)
            add(INVERSE_TANGENT)
            add(ARGUMENT)
            add(COSINE)
            add(HYPERBOLIC_COSINE)
            add(COTANGENT)
            add(HYPERBOLIC_COTANGENT)
            add(COSECANT)
            add(DEGREES)
            add(DERMINANT)
            add(DIMENSION)
            add(EXPONENTIAL)
            add(GREATEST_COMMON_DIVISOR)
            add(HOMOMORPHISM)
            add(INFINUM)
            add(KERNEL)
            add(BASE_2_LOGARITHM)
            add(LIMIT)
            add(LIMIT_INFERIOR)
            add(LIMIT_SUPERIOR)
            add(NATURAL_LOGARITHM)
            add(LOGARITHM)
            add(MAXIMUM)
            add(MINIMUM)
            add(PROBABILITY)
            add(INVERSE_LIMIT)
            add(SECANT)
            add(SINE)
            add(HYPERBOLIC_SINE)
            add(SUPREMUM)
            add(TANGENT)
            add(HBOLICTANGENT)
            add(LIMIT_SUPERIOR_VARIANT)
            add(LIMIT_INFERIOR_VARIANT)
            add(DIRECT_LIMIT_VARIANT)
            add(INVERSE_LIMIT_VARIANT)
        }

        createCategory("Relations") {
            add(BOWTIE)
            add(JOIN)
            add(PROPORTIONAL_TO)
            add(PROPORTIONAL_TO_SYMBOL)
            add(WASYSYM_PROPTO)
            add(MULTIMAP)
            add(PITCHFORK)
            add(THEREFORE)
            add(BECAUSE)
            add(DryUiEntry(description = "equals sign", "=", "misc_equals.png", "=", true))
            add(NOT_EQUAL)
            add(EQUIVALENT)
            add(APPROX)
            add(TILDE_OPERATOR)
            add(NOT_SIM)
            add(SIM_EQUALS)
            add(BACKWARDS_SIM_EQUALS)
            add(APPROX_EQUALS)
            add(CONG)
            add(NOT_CONG)
            add(COLON_EQUALSS)
            add(EQUALSS_COLON)
            add(COLON_EQUALS)
            add(EQUALS_COLON)
            add(COLON_APPROX)
            add(COLON_SIM)
            add(DOUBLE_COLON)
            add(DOUBLE_COLON_EQUALSS)
            add(EQUALSS_DOUBLE_COLON)
            add(DOUBLE_COLON_EQUALS)
            add(EQUALS_DOUBLE_COLON)
            add(DOUBLE_COLON_APPROX)
            add(DOUBLE_COLON_SIM)
            add(CIRCLE_EQUALS)
            add(TRIANGLE_EQUALS)
            add(EQUALS_CIRCLE)
            add(BUMP_EQUALS)
            add(DOUBLE_BUMP_EQUALS)
            add(DOT_EQUALS_DOT)
            add(RISING_DOTS_EQUALS)
            add(FALLING_DOTS_EQUALS)
            add(DOT_EQUALS)
            add(SMILE)
            add(FROWN)
            add(ASYMP)
            add(SMALL_FROWN)
            add(SMALL_SMILE)
            add(BETWEEN)
            add(PRECEDES)
            add(SUCCEEDS)
            add(NOT_PRECEEDS)
            add(NOT_SUCCEEDS)
            add(PRECEDES_OR_EQUAL)
            add(SUCCEEDS_OR_EQUALS)
            add(NOT_PRECEDES_OR_EQUALS)
            add(NOT_SUCCEEDS_OR_EQUALS)
            add(CURLY_PRECEDES_OR_EQUALS)
            add(CURLY_SUCCEEDS_OR_EQUALS)
            add(CURLY_EQUALS_PRECEDES)
            add(CURLY_EQUALS_SUCCEEDS)
            add(PRECEDES_SIM)
            add(SUCCEEDS_SIM)
            add(PRECEDES_NOT_SIM)
            add(SUCCEEDS_NOT_SIM)
            add(PRECEDES_APPROX)
            add(SUCCEEDS_APPROX)
            add(PRECEDES_NOT_APPROX)
            add(SUCCEEDS_NOT_APPROX)
            add(PERPENDICULAR)
            add(RIGHT_TACK)
            add(LEFT_TACK)
            add(NOT_RIGHT_TACK)
            add(FORCES)
            add(TRIPLE_RIGHT_TACK)
            add(MODELS)
            add(VERTICAL_DOUBLE_DASH_RIGHT)
            add(NOT_VERTICAL_DOUBLE_DASH_RIGHT)
            add(NOT_DOUBLE_VERTICAL_DOUBLE_DASH_RIGHT)
            add(MID)
            add(NOT_MID)
            add(PARALLEL)
            add(NOT_PARALLEL)
            add(MID_SHORT)
            add(NOT_MID_SHORT)
            add(NOT_PARALLEL_SHORT)
            add(DryUiEntry(description = "less than", "<", "misc_lesser.png", "<", true))
            add(DryUiEntry(description = "greater than", ">", "misc_greater.png", ">", true))
            add(NOT_LESS_THAN)
            add(NOT_GREATER_THAN)
            add(LESS_THAN_DOT)
            add(GREATER_THAN_DOT)
            add(DOUBLE_LESS_THAN)
            add(DOUBLE_GREATER_THAN)
            add(LESS_LESS_LESS)
            add(GREATER_GREATER_GREATER)
            add(LESS_THAN_EQUAL)
            add(GREATER_THAN_EQUAL)
            add(LESS_THAN_NOT_EQUAL)
            add(GREATER_THAN_NOT_EQUAL)
            add(NOT_LESS_THAN)
            add(NOT_GREATER_THAN)
            add(LESS_THAN_EQUALL)
            add(GREATER_THAN_EQUALL)
            add(LESS_NOT_EQUAL)
            add(GREATER_NOT_EQUAL)
            add(LESS_THAN_VERTICAL_NOT_EQUALS)
            add(GREATER_THAN_VERTICAL_NOT_EQUALS)
            add(NOT_LESS_THAN_EQUALL)
            add(NOT_GREATER_THAN_EQUALL)
            add(LESS_THAN_EQUALS_SLANT)
            add(GREATER_THAN_EQUALS_SLANT)
            add(NOT_LESS_THAN_EQUALS_SLANT)
            add(NOT_GREATER_THAN_EQUALS_SLANT)
            add(EQUALS_SLANT_LESS_THAN)
            add(EQUALS_SLANT_GREATER_THAN)
            add(LESS_GREATER)
            add(GREATER_LESS)
            add(LESS_EQUALS_GREATER)
            add(GREATER_EQUALS_LESSER)
            add(LESS_EQUALSS_GREATER)
            add(GREATER_EQUALSS_LESSER)
            add(LESS_SIM)
            add(GREATER_SIM)
            add(LESS_NOT_SIM)
            add(GREATER_NOT_SIM)
            add(LESS_APPROX)
            add(GREATER_APPROX)
            add(LESS_NOT_APPROX)
            add(GREATER_NOT_APPROX)
            add(TRIANGLE_LEFT_VARIATION)
            add(TRIANGLE_RIGHT_VARIATION)
            add(NOT_TRIANGLE_LEFT)
            add(NOT_TRIANGLE_RIGHT)
            add(TRIANGLE_LEFT_EQUALS)
            add(TRIANGLE_RIGHT_EQUALS)
            add(TRIANGLE_LEFT_EQUALS_SLANT)
            add(TRIANGLE_RIGHT_EQUALS_SLANT)
            add(NOT_TRIANGLE_LEFT_EQUALS)
            add(NOT_TRIANGLE_RIGHT_EQUALS)
            add(NOT_TRIANGLE_LEFT_EQUALS_SLANT)
            add(NOT_TRIANGLE_RIGHT_SLANT)
            add(BLACK_TRIANGLE_LEFT)
            add(BLACK_TRIANGLE_RIGHT)
            add(SUBSET)
            add(SUPERSET)
            add(SUBSET_EQUALS)
            add(SUPERSET_EQUALS)
            add(SUBSET_NOT_EQUALS)
            add(SUPERSET_NOT_EQUALS)
            add(SUBSET_NOT_EQUALS_VARIATION)
            add(SUPERSET_NOT_EQUALS_VARIATION)
            add(NOT_SUBSET_EQUALS)
            add(NOT_SUPERSET_EQUALS)
            add(SUBSET_EQUALSS)
            add(SUPERSET_EQUALSS)
            add(SUBSET_NOT_EQUALSS)
            add(SUPERSET_NOT_EQUALSS)
            add(NOT_SUBSET_EQUALSS)
            add(NOT_SUPERSET_EQUALSS)
            add(SUBSET_PLUS)
            add(SUBSET_PLUS_EQUALS)
            add(SUPERSET_PLUS)
            add(SUPERSET_PLUS_EQUALS)
            add(REVERSED_EPSILON)
            add(DOUBLE_SUBSET)
            add(DOUBLE_SUPERSET)
            add(SQUARE_SUBSET)
            add(SQUARE_SUPERSET)
            add(SQUARE_SUBSET_EQUALS)
            add(SQUARE_SUPERSET_EQUALS)
            add(IN_PLUS)
            add(REVERSED_IN_PLUS)
        }

        createCategory("Arrows") {
            add(LEFT_ARROW)
            add(LEFT_AND_RIGHT_ARROW)
            add(RIGHT_ARROW)
            add(LONG_LEFT_ARROW)
            add(LONG_LEFT_AND_RIGHT_ARROW)
            add(LONG_RIGHT_ARROW)
            add(SHORT_LEFT_ARROW)
            add(SHORT_RIGHT_ARROW)
            add(UP_ARROW)
            add(UP_AND_DOWN_ARROW)
            add(DOWN_ARROW)
            add(SHORT_DOWN_ARROW)
            add(SHORT_UP_ARROW)
            add(NORTH_NORTH_EAST_ARROW)
            add(NORTH_EAST_ARROW)
            add(SOUTH_EAST_ARROW)
            add(SOUNT_SOUTH_EAST_ARROW)
            add(SOUTH_SOUTH_WEST_ARROW)
            add(SOUTH_WEST_ARROW)
            add(NORTH_WEST_ARROW)
            add(NORTH_NORTH_WEST_ARROW)
            add(MAPS_TO)
            add(MAPS_FROM)
            add(MAPS_TO_DOUBLE_ARROW)
            add(MAPS_FROM_DOUBLE_ARROW)
            add(LONG_MAPS_TO_DOUBLE_ARROW)
            add(LONG_MAPS_FROM_DOUBLE_ARROW)
            add(LONG_MAPS_TO)
            add(LONG_MAPS_FROM)
            add(NOT_LEFT_ARROW)
            add(NOT_LEFT_AND_RIGHT_ARROW)
            add(NOT_RIGHT_ARROW)
            add(LEFT_ARROW_WITH_HOOK)
            add(RIGHT_ARROW_WITH_HOOK)
            add(TWO_HEADED_LEFT_ARROW)
            add(TWO_HEADED_RIGHT_ARROW)
            add(LEFT_ARROW_WITH_TAIL)
            add(RIGHT_ARROW_WITH_TAIL)
            add(LEFT_DOUBLE_ARROW)
            add(LEFT_AND_RIGHT_DOUBLE_ARROW)
            add(RIGHT_DOUBLE_ARROW)
            add(UP_DOUBLE_ARROW)
            add(UP_AND_DOWN_DOUBLE_ARROW)
            add(DOWN_DOUBLE_ARROW)
            add(LONG_LEFT_DOUBLE_ARROW)
            add(LONG_LEFT_AND_RIGHT_DOUBLE_ARROW)
            add(LONG_RIGHT_ARROW)
            add(NOT_LEFT_DOUBLE_ARROW)
            add(NOT_LEFT_AND_RIGHT_DOUBLE_ARROW)
            add(NOT_RIGHT_DOUBLE_ARROW)
            add(LEFT_ARROW_PAIR)
            add(LEFT_AND_RIGHT_ARROW_PAIR)
            add(RIGHT_AND_LEFT_ARROW_PAIR)
            add(RIGHT_ARROW_PAIR)
            add(UP_ARROW_PAIR)
            add(DOWN_ARROW_PAIR)
            add(ANTI_CLOCKWISE_CIRCLE_ARROW)
            add(CLOCKWISE_CIRCLE_ARROW)
            add(ANTI_CLOCKWISE_SEMICIRCLE_ARROW_TOP)
            add(CLOCKWISE_SEMICIRCLE_ARROW_TOP)
            add(UP_ARROW_WITH_LEFT_TIP)
            add(UP_ARROW_WITH_RIGHT_TIP)
            add(LEFT_ARROW_WITH_LOOP)
            add(RIGHT_ARROW_WITH_LOOP)
            add(DASHED_LEFT_ARROW)
            add(DASHED_RIGHT_ARROW)
            add(LEFT_AND_RIGHT_WAVE_ARROW)
            add(RIGHT_WAVE_ARROW)
            add(LEFT_TRIPLE_ARROW)
            add(LEFT_HARPOON_DOWN)
            add(RIGHT_HARPOON_DOWN)
            add(LEFT_HARPOON_UP)
            add(RIGHT_HARPOON_UP)
            add(RIGHT_AND_LEFT_HARPOONS)
            add(LEFT_AND_RIGHT_HARPOONS)
            add(UP_HARPOON_LEFT)
            add(DOWN_HARPOON_LEFT)
            add(UP_HARPOON_RIGHT)
            add(DOWN_HARPOON_RIGHT)
            add(LatexRegularCommand.TEXT_LEFT_ARROW)
            add(LatexRegularCommand.TEXT_RIGHT_ARROW)
            add(LatexRegularCommand.TEXT_UP_ARROW)
            add(LatexRegularCommand.TEXT_DOWN_ARROW)
            add(LEFT_OPEN_HEADED_ARROW)
            add(LEFT_AND_RIGHT_OPEN_HEADED_ARROW)
            add(RIGHT_OPEN_HEADED_ARROW)
            add(LEFT_AND_RIGHT_ARROW_EQUALS)
            add(LIGHTNING)
        }

        createCategory("Delimiters") {
            // Place the enclosing delimiters at the front:
            // 1. Makes the symbol overview less cluttered.
            // 2. Encourages usage of \left\right over standalone symbols.
            add(DryUiEntry(description = "brackets", "\\left[ <caret> \\right]", "misc_brackets_pair.png", "\\left[...\\right]", true))
            addLeftRight(LEFT_BRACKET_BOLD, RIGHT_BRACKET_BOLD, "bold brackets", "misc_bold_brackets_pair.png")
            addLeftRight(LEFT_ANGLE_BRACKET, RIGHT_ANGLE_BRACKET, "angular brackets", "misc_angular_brackets_pair.png")
            add(DryUiEntry(description = "absolute value", "\\left| <caret> \\right|", "misc_absolute_value_pair.png", "\\left|...\\right|", true))
            addLeftRight(DOUBLE_VERTICAL_LINE, DOUBLE_VERTICAL_LINE, "length", "misc_length_pair.png")
            addLeftRight(LEFT_CEIL, RIGHT_CEIL, "ceiling", "misc_ceiling_pair.png")
            addLeftRight(LEFT_CEIL_BOLD, RIGHT_CEIL_BOLD, "bold ceiling", "misc_bold_ceiling_pair.png", requireLeftRight = false)
            addLeftRight(LEFT_FLOOR, RIGHT_FLOOR, "floor", "misc_floor_pair.png")
            addLeftRight(LEFT_FLOOR_BOLD, RIGHT_FLOOR_BOLD, "bold floor", "misc_bold_floor_pair.png", requireLeftRight = false)
            add(DryUiEntry(description = "parentheses", "\\left( <caret> \\right)", "misc_parentheses_pair.png", "\\left(...\\right)", true))
            addLeftRight(LEFT_PARENTHESIS_BOLD, RIGHT_PARENTHESIS_BOLD, "bold parentheses", "misc_bold_parentheses_pair.png", requireLeftRight = false)
            add(DryUiEntry(description = "braces", "\\left\\{ <caret> \\right\\}", "misc_braces_pair.png", "\\left\\{...\\right\\}", true))
            addLeftRight(LEFT_MOUSTACHE, RIGHT_MOUSTACHE, "moustaches", "misc_moustaches_pair.png")
            addLeftRight(LEFT_GROUP, RIGHT_GROUP, "group", "misc_group_pair.png")
            addLeftRight(BRACEVERT, BRACEVERT, "bracevert", "misc_bracevert_pair.png")
            addLeftRight(LEFT_BAG, RIGHT_BAG, "bag", "misc_bag_pair.png", requireLeftRight = false)
            addLeftRight(LEFT_BAG_BOLD, RIGHT_BAG_BOLD, "bold bag", "misc_bold_bag_pair.png", requireLeftRight = false)

            // Single delimiters.
            add(DryUiEntry(description = "left bracket", "[", "misc_left_bracket.png", "[", true))
            add(DryUiEntry(description = "right bracket", "]", "misc_right_bracket.png", "]", true))
            add(LEFT_BRACKET_BOLD)
            add(RIGHT_BRACKET_BOLD)
            add(LEFT_ANGLE_BRACKET)
            add(RIGHT_ANGLE_BRACKET)
            add(DryUiEntry(description = "vertical line", "|", "misc_vertical_line.png", "|", true))
            add(DOUBLE_VERTICAL_LINE)
            add(LEFT_CEIL)
            add(RIGHT_CEIL)
            add(LEFT_CEIL_BOLD)
            add(RIGHT_CEIL_BOLD)
            add(LEFT_FLOOR)
            add(RIGHT_FLOOR)
            add(LEFT_FLOOR_BOLD)
            add(RIGHT_FLOOR_BOLD)
            add(DryUiEntry(description = "left parenthesis", "(", "misc_left_parenthesis.png", "(", true))
            add(DryUiEntry(description = "right parenthesis", ")", "misc_right_parenthesis.png", ")", true))
            add(LEFT_PARENTHESIS_BOLD)
            add(RIGHT_PARENTHESIS_BOLD)
            add(DryUiEntry(description = "left brace", "\\{", "misc_left_brace.png", "\\{", true))
            add(DryUiEntry(description = "right brace", "\\}", "misc_right_brace.png", "\\}", true))
            add(DryUiEntry(description = "forward slash", "/", "misc_forward_slash.png", "/", true))
            add(BACKSLASH)
            add(LEFT_MOUSTACHE, latex = "\\left\\lmoustache", image = "\\left\\lmoustache\\right.")
            add(RIGHT_MOUSTACHE, latex = "\\right\\rmoustache", image = "\\left.\\right\\rmoustache")
            add(LEFT_GROUP, latex = "\\left\\lgroup", image = "\\left\\lgroup\\right.")
            add(RIGHT_GROUP, latex = "\\right\\rgroup", image = "\\left.\\right\\rgroup")
            add(BRACEVERT)
            add(LEFT_BAG)
            add(RIGHT_BAG)
            add(LEFT_BAG_BOLD)
            add(RIGHT_BAG_BOLD)
            add(UPPER_LEFT_CORNER)
            add(UPPER_RIGHT_CORNER)
            add(LOWER_LEFT_CORNER)
            add(LOWER_RIGHT_CORNER)
        }

        createCategory("Greek") {
            add(ALPHA)
            add(BETA)
            add(GAMMA)
            add(DELTA)
            add(BETTER_LOOKING_EPSILON)
            add(EPSILON)
            add(ZETA)
            add(ETA)
            add(THETA)
            add(THETA_VARIANT)
            add(IOTA)
            add(KAPPA)
            add(LAMBDA)
            add(MU)
            add(NU)
            add(XI)
            add(DryUiEntry(description = "omicron", "o", "misc_omicron.png", "o", true))
            add(PI)
            add(PI_VARIANT)
            add(RHO)
            add(RHO_VARIANT)
            add(SIGMA)
            add(FINAL_SIGMA)
            add(TAU)
            add(UPSILON)
            add(PHI_STRAIGHT)
            add(PHI_CURLY)
            add(CHI)
            add(PSI)
            add(OMEGA)
            add(DryUiEntry(description = "capital alpha", "A", "misc_capital_alpha.png", "A", true))
            add(DryUiEntry(description = "capital beta", "B", "misc_capital_beta.png", "B", true))
            add(CAPITAL_GAMMA)
            add(CAPITAL_GAMMA_VARIANT)
            add(CAPITAL_DELTA)
            add(CAPITAL_DELTA_VARIANT)
            add(DryUiEntry(description = "capital epsilon", "E", "misc_capital_epsilon.png", "E", true))
            add(DryUiEntry(description = "capital zeta", "Z", "misc_capital_zeta.png", "Z", true))
            add(DryUiEntry(description = "capital eta", "Z", "misc_capital_eta.png", "H", true))
            add(CAPITAL_THETA)
            add(CAPITAL_THETA_VARIANT)
            add(DryUiEntry(description = "capital iota", "I", "misc_capital_iota.png", "I", true))
            add(DryUiEntry(description = "capital kappa", "K", "misc_capital_kappa.png", "K", true))
            add(CAPITAL_LAMBDA)
            add(CAPITAL_LAMBDA_VARIANT)
            add(DryUiEntry(description = "capital mu", "M", "misc_capital_mu.png", "M", true))
            add(DryUiEntry(description = "capital nu", "M", "misc_capital_nu.png", "N", true))
            add(CAPITAL_XI)
            add(CAPITAL_XI_VARIANT)
            add(DryUiEntry(description = "capital omicron", "O", "misc_capital_omicron.png", "O", true))
            add(CAPITAL_PI)
            add(CAPITAL_PI_VARIANT)
            add(DryUiEntry(description = "capital rho", "P", "misc_capital_rho.png", "P", true))
            add(CAPITAL_SIGMA)
            add(CAPITAL_SIGMA_VARIANT)
            add(DryUiEntry(description = "capital tau", "T", "misc_capital_tau.png", "T", true))
            add(CAPITAL_UPSILON)
            add(CAPITAL_UPSILON_VARIANT)
            add(CAPITAL_PHI)
            add(CAPITAL_PHI_VARIANT)
            add(DryUiEntry(description = "capital chi", "X", "misc_capital_chi.png", "X", true))
            add(CAPITAL_PSI)
            add(CAPITAL_PSI_VARIANT)
            add(CAPITAL_OMEGA)
            add(CAPITAL_OMEGA_VARIANT)
        }

        createCategory("Misc. math") {

        }

        createCategory("Text") {

        }

        createCategory("Misc. symbols") {

        }
    }

    /**
     * The list of all registered categories.
     * Also contains the ALL category.
     */
    val categoryList: List<SymbolCategory> = listOf(SymbolCategory.ALL) + categories.map { (category, _) -> category }

    /**
     * Flat map of all registered symbols, in order.
     */
    val symbolList: List<SymbolUiEntry> = categories.flatMap { it.value }

    /**
     * Get the operators that are in the given category.
     * The category [SymbolCategory.ALL] returns all available symbols.
     */
    operator fun get(category: SymbolCategory): List<SymbolUiEntry> = if (category == SymbolCategory.ALL) {
        symbolList
    }
    else categories[category] ?: emptyList()

    /**
     * Adds a symbol entry for a Left/Right pair that inserts \leftX ... \rightX.
     *
     * @param requireLeftRight
     *          True if the commands should be preceded with \left and \right. False if the commands alone suffice.
     */
    fun MutableList<SymbolUiEntry>.addLeftRight(
            left: LatexCommand, right: LatexCommand, description: String, fileName: String, requireLeftRight: Boolean = true
    ) {
        val leftCmd = if (requireLeftRight) "\\left" else ""
        val rightCmd = if (requireLeftRight) "\\right" else ""
        add(DryUiEntry(
                description = description,
                generatedLatex = "$leftCmd${left.commandDisplay} <caret> $rightCmd${right.commandDisplay}",
                fileName = fileName,
                imageLatex = "$leftCmd${left.commandDisplay}...$rightCmd${right.commandDisplay}",
                isMathSymbol = true,
                dependency = left.dependency
        ))
    }

    /**
     * Adds a UI entry for the given command to the entry list.
     * For the parameters see [CommandUiEntry].
     */
    private fun MutableList<SymbolUiEntry>.add(
            command: LatexCommand,
            latex: String? = null,
            fileName: String? = null,
            description: String? = null,
            image: String? = null
    ) = add(command.toEntry(latex, fileName, description, image))


    /**
     * Turns the command into a ui entry.
     */
    private fun LatexCommand.toEntry(
            latex: String? = null,
            fileName: String? = null,
            description: String? = null,
            image: String? = null
    ) = CommandUiEntry(this, latex, fileName, description, image)

    /**
     * Adds a new category to the map and initializes the symbols.
     */
    private fun MutableMap<SymbolCategory, List<SymbolUiEntry>>.createCategory(
            name: String,
            description: String = name,
            symbolInitializer: MutableList<SymbolUiEntry>.() -> Unit
    ) {
        val category = SymbolCategory(name, description)
        this[category] = ArrayList<SymbolUiEntry>().apply {
            symbolInitializer()
        }
    }
}