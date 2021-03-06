PLANT_UML_PATH="/Users/jeanloicdejeager/Downloads/plantuml.jar"
PLANT_UML_SRC_FOLDER="img/src"
PLANT_UML_OUTPUT_FOLDER="img/output"

for file in "$PLANT_UML_SRC_FOLDER"/*
do
  java -DPLANTUML_LIMIT_SIZE=10000 -jar $PLANT_UML_PATH $file
  FILE_NAME_NO_EXTENSION="$(cut -d'.' -f1 <<<"$file")"
  mv "$FILE_NAME_NO_EXTENSION.png" $PLANT_UML_OUTPUT_FOLDER
done