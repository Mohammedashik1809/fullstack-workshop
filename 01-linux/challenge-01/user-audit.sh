#!/bin/bash

echo "=== User Audit Report ==="


TOTAL_USERS=$(wc -l < /etc/passwd)
echo "Total users: $TOTAL_USERS"


SHELL_USERS=$(awk -F: '$7 !~ /nologin|false/ {count++} END {print count}' /etc/passwd)
echo "Users with shell access: $SHELL_USERS"


NO_PASS_USERS=$(awk -F: '$2 == "" {print $1}' /etc/shadow 2>/dev/null)

NO_PASS_COUNT=$(echo "$NO_PASS_USERS" | grep -c .)
echo "Users without password: $NO_PASS_COUNT"

if [ "$NO_PASS_COUNT" -gt 0 ]; then
    echo "$NO_PASS_USERS" | sed 's/^/  - /'
fi


echo "Last login info for shell users:"

awk -F: '$7 !~ /nologin|false/ {print $1}' /etc/passwd | while read USER
do
    LAST_LOGIN=$(lastlog -u "$USER" | awk 'NR==2 {print $4, $5, $6}')
    if [ -z "$LAST_LOGIN" ]; then
        LAST_LOGIN="Never logged in"
    fi
    echo "  - $USER: $LAST_LOGIN"
done
