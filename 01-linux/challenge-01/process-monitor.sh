#!/bin/bash

# Exit immediately if any command fails
set -e


# Validate number of command-line arguments
if [ "$#" -ne 2 ]; then
    echo "Usage: $0 <process_name> <check_interval_seconds>"
    exit 1
fi

# Assign arguments to variables
PROCESS_NAME="$1"
INTERVAL="$2"

# Validate that interval is a positive number
if ! [[ "$INTERVAL" =~ ^[0-9]+$ ]]; then
    echo "Error: Interval must be a numeric value"
    exit 1
fi

echo "Monitoring process: $PROCESS_NAME"
echo "Checking every $INTERVAL seconds..."
echo "----------------------------------"

# Infinite monitoring loop
while true; do
    # Capture current timestamp
    TIMESTAMP=$(date "+%Y-%m-%d %H:%M:%S")

    # Check if process is running
    if pgrep "$PROCESS_NAME" > /dev/null; then
        echo "[$TIMESTAMP] Process '$PROCESS_NAME' is RUNNING"
    else
        echo "[$TIMESTAMP]  Process '$PROCESS_NAME' has STOPPED!"
        echo "Exiting monitor..."
        exit 0
    fi

    # Wait before next check
    sleep "$INTERVAL"
done

