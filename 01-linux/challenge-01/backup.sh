#!/bin/bash

# Exit immediately if any command fails
set -e


# Check for exactly 2 command-line arguments
if [ "$#" -ne 2 ]; then
    echo "Usage: $0 <source_directory> <backup_destination>"
    exit 1
fi

# Assign arguments to variables
SOURCE_DIR="$1"
BACKUP_DIR="$2"

# Validate that the source directory exists
if [ ! -d "$SOURCE_DIR" ]; then
    echo "Error: Source directory does not exist."
    exit 1
fi

# Create backup destination directory if it does not exist
mkdir -p "$BACKUP_DIR"

# Generate a timestamp for unique backup naming
TIMESTAMP=$(date +"%Y%m%d-%H%M%S")

# Define backup file name
BACKUP_FILE="$BACKUP_DIR/backup-$TIMESTAMP.tar.gz"

# Create compressed backup
tar -czf "$BACKUP_FILE" -C "$SOURCE_DIR" .

# Get the size of the backup file
BACKUP_SIZE=$(du -h "$BACKUP_FILE" | cut -f1)

# Display success message
echo " Backup completed successfully."
echo " Backup file: $BACKUP_FILE"
echo " Backup size: $BACKUP_SIZE"

# Keep only the latest 5 backups and delete older ones
ls -1t "$BACKUP_DIR"/backup-*.tar.gz | tail -n +6 | xargs -r rm --

echo " Old backups cleaned (keeping last 5)."
