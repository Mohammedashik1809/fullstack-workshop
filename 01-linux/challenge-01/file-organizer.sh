#!/bin/bash

# Exit immediately if any command fails
set -e


# Check if directory argument is provided
if [ "$#" -eq 0 ]; then
    echo "Usage: $0 <directory_path>"
    exit 1
fi

# Assign argument to variable
DIR="$1"

# Validate that the provided path is a directory
if [ ! -d "$DIR" ]; then
    echo "Error: '$DIR' is not a valid directory"
    exit 1
fi

# Declare an associative array to count files per extension
declare -A count

# Loop through all items in the directory
for file in "$DIR"/*; do
    # Process only regular files
    if [ -f "$file" ]; then

        # Extract file extension
        ext="${file##*.}"

        # Create directory for the extension if it doesn't exist
        mkdir -p "$DIR/$ext"

        # Move file into its respective extension directory
        mv "$file" "$DIR/$ext/"

        # Increment count for the extension
        ((count["$ext"]++))
    fi
done

# Display summary
echo "---- Summary ----"
for ext in "${!count[@]}"; do
    echo "Organized ${count[$ext]} .$ext files"
done

