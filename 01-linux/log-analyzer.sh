#!/bin/bash
LOG_FILE=$1

if [ $# -eq 0 ]; then
    echo "Please provide log file"
    exit 1
fi


if [ ! -f "$LOG_FILE" ]; then
   echo "File not found!"
    exit 1
fi


TOTAL_LINES=$(wc -l < "$LOG_FILE")


INFO_COUNT=$(grep -c "INFO" "$LOG_FILE")
WARNING_COUNT=$(grep -c "WARNING" "$LOG_FILE")
ERROR_COUNT=$(grep -c "ERROR" "$LOG_FILE")


IP_LIST=$(grep -oE '[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}' "$LOG_FILE" | sort | uniq)


echo "========== LOG ANALYSIS REPORT =========="
echo "File: $LOG_FILE"
echo "Total Lines: $TOTAL_LINES"
echo "----------------------------------------"
echo "INFO:    $INFO_COUNT"
echo "WARNING: $WARNING_COUNT"
echo "ERROR:   $ERROR_COUNT"
echo "----------------------------------------"

if [ -z "$IP_LIST" ]; then
    echo "None"
else
    echo "Unique IP Addresses Found:"
    for ip in $IP_LIST
    do
        echo " - $ip"
    done
fi

echo "========================================"
