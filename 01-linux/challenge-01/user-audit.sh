#!/bin/bash

# Exit immediately if any command fails
set -e

echo "=== User Audit Report ==="
echo

# Count total users from /etc/passwd
TOTAL_USERS=$(wc -l < /etc/passwd)
echo "Total users: $TOTAL_USERS"

# Count users who have valid shell access
SHELL_USERS=$(awk -F: '$7 !~ /nologin|false/ {count++} END {print count}' /etc/passwd)
echo "Users with shell access: $SHELL_USERS"

# Identify users without passwords (requires root privileges)
NO_PASS_USERS=$(awk -F: '$2 == "" {print $1}' /etc/shadow 2>/dev/null || true)

# Count users without passwords
NO_PASS_COUNT=$(echo "$NO_PASS_USERS" | grep -c . || true)
echo "Users without password: $NO_PASS_COUNT"

# Display users without passwords if any exist
if [ "$NO_PASS_COUNT" -gt 0 ]; then
    echo "Users without password set:"
    echo "$NO_PASS_USERS" | sed 's/^/  - /'
fi

echo
echo "Last login info for shell users:"

# Loop through shell-enabled users and display last login info
awk -F: '$7 !~ /nologin|false/ {print $1}' /etc/passwd | while read -r USER
do
    LAST_LOGIN=$(lastlog -u "$USER" | awk 'NR==2 {print $4, $5, $6}')

    # Handle users who have never logged in
    if [ -z "$LAST_LOGIN" ]; then
        LAST_LOGIN="Never logged in"
    fi

    echo "  - $USER: $LAST_LOGIN"
done

