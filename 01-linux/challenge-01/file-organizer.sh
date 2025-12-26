#!/bin/bash

if [ $# -eq 0 ]; then
    echo "Usage: ./file-organizer.sh <directory_path>"
    exit 1
fi

DIR=$1

if [ ! -d "$DIR" ]; then
    echo "Error: '$DIR' is not a valid directory"
    exit 1
fi

declare -A count

for file in "$DIR"/*; do
        if [ -f "$file" ]; then
        ext="${file##*.}"

        mkdir -p "$DIR/$ext"

        mv "$file" "$DIR/$ext/"

        ((count[$ext]++))
    fi
done


echo "---- Summary ----"
for ext in "${!count[@]}"; do
    echo "Organized ${count[$ext]} .$ext files"
done
