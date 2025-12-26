#!/bin/bash

if [ $# -ne 2 ]; then
    echo "Usage: $0 <process_name> <check_interval_seconds>"
    exit 1
fi

PROCESS_NAME=$1
INTERVAL=$2

if ! [[ "$INTERVAL" =~ ^[0-9]+$ ]]; then
    echo "Error: Interval must be a number"
    exit 1
fi

echo "Monitoring process: $PROCESS_NAME"
echo "Checking every $INTERVAL seconds..."
echo "----------------------------------"

while true
do
    TIMESTAMP=$(date "+%Y-%m-%d %H:%M:%S")

    if pgrep "$PROCESS_NAME" > /dev/null; then
        echo "[$TIMESTAMP] Process '$PROCESS_NAME' is RUNNING"
    else
        echo "[$TIMESTAMP] Process '$PROCESS_NAME' has STOPPED!"
        echo "Exiting monitor..."
        exit 0
    fi

    sleep "$INTERVAL"
done
