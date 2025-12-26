#!/bin/bash

THRESHOLD=${1:-90}
ALERT=0

echo "Disk Usage Check (Threshold: ${THRESHOLD}%)"
echo "--------------------------------------------"

# Use df with percentage only and ignore Windows paths
df -P | grep '^/' | awk '{print $1, $5}' | while read FILESYSTEM USAGE
do
    USAGE_VALUE=${USAGE%\%}

    if [ "$USAGE_VALUE" -gt "$THRESHOLD" ]; then
        echo "WARNING: $FILESYSTEM is at ${USAGE_VALUE}% (threshold: ${THRESHOLD}%)"
        ALERT=1
    else
        echo "OK: $FILESYSTEM is at ${USAGE_VALUE}%"
    fi
done

if [ "$ALERT" -eq 1 ]; then
    exit 1
else
    exit 0
fi

