@echo off
setlocal enabledelayedexpansion

REM Define the base URL
set "baseUrl=https://example.com/directory/"

REM Fetch the directory listing and save to a temporary file
curl %baseUrl% -o dir_listing.html

REM Parse the HTML to extract file links and save to a temporary file
findstr /r /c:"href=\"[^\"]*\"" dir_listing.html > file_links.txt

REM Loop through each line in the file and download the files
for /f "tokens=2 delims=\"\"%%A in (file_links.txt) do (
    set "fileLink=%%A"
    REM Remove leading and trailing quotes
    set "fileLink=!fileLink:~1,-1!"
    
    REM Construct the full file URL
    set "fileUrl=%baseUrl%!fileLink!"

    REM Download the file
    curl -O !fileUrl!
)

REM Clean up temporary files
del dir_listin
