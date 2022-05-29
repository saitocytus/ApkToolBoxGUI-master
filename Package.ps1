$CURRENT_DIR = Split-Path -Parent $MyInvocation.MyCommand.Definition
$TARGET_DIR = "$CURRENT_DIR/target"
$RELEASE_JAR_FILE_Filter = 'APKToolBoxGUI-*.jar'
$RELEASE_JAR_FILE = Get-ChildItem -Path "$TARGET_DIR" -Filter $RELEASE_JAR_FILE_Filter
$VERSION = $RELEASE_JAR_FILE.BaseName.Replace("APKToolBoxGUI-", "").Replace(".jar", "")
$TMP_DIR = "$TARGET_DIR/release"

# Builed $TARGET_DIR/APKToolBoxGUI-$VERSION-without-JRE.zip
if (Test-Path $TMP_DIR) {
    Remove-Item $TMP_DIR -Recurse -Force
}
If (!(Test-Path $TMP_DIR)) {
    New-Item -ItemType Directory -Force -Path $TMP_DIR
}

Copy-Item $RELEASE_JAR_FILE.FullName "$TMP_DIR/APKToolBoxGUI.jar"

Copy-Item "$CURRENT_DIR/ApkToolBoxGUI.bat" $TMP_DIR
Copy-Item "$CURRENT_DIR/ApkToolBoxGUI.sh" $TMP_DIR
Copy-Item "$CURRENT_DIR/apktoolboxgui.properties" $TMP_DIR

Compress-Archive -Path "$TMP_DIR\*" -DestinationPath "$TARGET_DIR/APKToolBoxGUI-$VERSION-without-JRE.zip" -Force


# Builed $TARGET_DIR/APKToolBoxGUI-$VERSION-with-JRE.zip
if (Test-Path $TMP_DIR) {
    Remove-Item $TMP_DIR -Recurse -Force
}
If (!(Test-Path $TMP_DIR)) {
    New-Item -ItemType Directory -Force -Path $TMP_DIR
}
if (Test-Path "$TARGET_DIR/ApkToolBoxGUI-$VERSION-with-JRE") {
    Remove-Item "$TARGET_DIR/ApkToolBoxGUI-$VERSION-with-JRE" -recurse
}

Copy-Item $RELEASE_JAR_FILE.FullName "$TMP_DIR/APKToolBoxGUI.jar"

jpackage --input $TMP_DIR --type app-image --name "ApkToolBoxGUI-$VERSION-with-JRE" --main-jar 'APKToolBoxGUI.jar' --dest "$TARGET_DIR" --verbose

Copy-Item "$CURRENT_DIR/apktoolboxgui.properties" "$TARGET_DIR/ApkToolBoxGUI-$VERSION-with-JRE"

Compress-Archive -Path "$TARGET_DIR/ApkToolBoxGUI-$VERSION-with-JRE\*" -DestinationPath "$TARGET_DIR/APKToolBoxGUI-$VERSION-with-JRE.zip" -Force

if (Test-Path $TMP_DIR) {
    Remove-Item $TMP_DIR -Recurse -Force
}

if (Test-Path "$TARGET_DIR/ApkToolBoxGUI-$VERSION-with-JRE") {
    Remove-Item "$TARGET_DIR/ApkToolBoxGUI-$VERSION-with-JRE" -Recurse -Force
}