

TYPE=$(scripts/gum choose "fix" "feat" "docs" "style" "refactor" "test" "chore" "revert")
SCOPE=$(scripts/gum input --placeholder "scope")

# Since the scope is optional, wrap it in parentheses if it has a value.
test -n "$SCOPE" && SCOPE="($SCOPE)"

# Pre-populate the input with the type(scope): so that the user may change it
SUMMARY=$(scripts/gum input --value "$TYPE$SCOPE: " --placeholder "Summary of this change")
DESCRIPTION=$(scripts/gum write --placeholder "Details of this change")

# Commit these changes if user confirms
scripts/gum confirm "Commit changes?" && git commit -m "$SUMMARY" -m "$DESCRIPTION"