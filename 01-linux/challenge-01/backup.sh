#!/bin/bash

if [ $# -ne 2 ]; then
    echo "Usage: $0 <source_directory> <backup_destination>"
    exit 1
fi

SOURCE_DIR=$1
BACKUP_DIR=$2

if [ ! -d "$SOURCE_DIR" ]; then
    echo "Error: Source directory does not exist."
    exit 1
fi

mkdir -p "$BACKUP_DIR"

TIMESTAMP=$(date +"%Y%m%d-%H%M%S")

BACKUP_FILE="$BACKUP_DIR/backup-$TIMESTAMP.tar.gz"

tar -czf "$BACKUP_FILE" -C "$SOURCE_DIR" .

if [ $? -ne 0 ]; then
    echo "Backup failed!"
    exit 1
fi

BACKUP_SIZE=$(du -h "$BACKUP_FILE" | cut -f1)
echo "Backup completed successfully."
echo "Backup file: $BACKUP_FILE"
echo "Backup size: $BACKUP_SIZE"

ls -1t "$BACKUP_DIR"/backup-*.tar.gz | tail -n +6 | xargs -r rm --

echo "Old backups cleaned (keeping last 5)."
