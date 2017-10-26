# Git Hooks

It is necessary to wire up the git hooks to your local `.git` file path. You can do so easily with the following:

`ln -s ./hooks/pre-commit-* .git/hooks/`

This works for macOS and other *nix-y variants. Windows will require additional steps.
