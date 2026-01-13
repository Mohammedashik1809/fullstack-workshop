#!/bin/bash

# Exit immediately if any command fails
set -e



# Read threshold from command-line argument or default to 90%
THRESHOLD="${1:-90}"

# Alert flag (0 = no alert, 1 = alert triggered)
ALERT=0

echo "Disk Usage Check (Threshold: ${THRESHOLD}%)"
echo "--------------------------------------------"

# Get filesystem usage:
# - df -P : POSIX format
# - grep '^/' : ignore non-Unix / Windows paths
# - awk : extract filesystem name and usage %
df -P | grep '^/' | awk '{print $1, $5}' | while read -r FILESYSTEM USAGE
do
    # Remove % symbol from usage value
    USAGE_VALUE="${USAGE%\%}"

    # Compare usage with threshold
    if [ "$USAGE_VALUE" -gt "$THRESHOLD" ]; then
        echo "  WARNING: $FILESYSTEM is at ${USAGE_VALUE}% (threshold: ${THRESHOLD}%)"
        ALERT=1
    else
        echo " OK: $FILESYSTEM is at ${USAGE_VALUE}%"
    fi
done

# Exit with status based on alert condition
if [ "$ALERT" -eq 1 ]; then
    exit 1
else
    exit 0
fi

