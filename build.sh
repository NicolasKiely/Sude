# Distribution directory
distDir="./dist"

# Source code directory
srcDir="src"

# Data directory
dataDir="data"

# Final jar name
finalName="Sude-v0.1"

# Dependencies
depend="./dependencies/bukkit-0.0.1-SNAPSHOT.jar:./dependencies/Permissions.jar"



# Parameter: File/folder name
# Recurisively compiles source code in folder
function handle {
  if [ -d $1 ]; then
  	
  	# Create mirror folder in distribution
  	#mkdir $distDir/$1
  	
  	# Recursively handle all files in this directory
  	for i in $(ls $1); do
  		handle "$1/$i"
  	done
  	
  elif [ -f $1 ]; then
  	
  	# Check for file type
  	if [ ""$(echo $1 | grep ".java$") == "" ]; then
  		echo "Non-java file $1"
  		
  	else
  		echo "Java file $1"
  		
  		# Find output class directory
  		classDir=$distDir/$1
  		
  		# Compile source file
  		javac -cp $depend -d $distDir -sourcepath $srcDir $1
  		
  		if [ "$?" != "0" ]; then
  			exit 1
  		fi
  		
  	fi
  
  else
  	echo "Idunno what this is: $1"
  	
  fi
}

if [ -d $distDir ]; then
	# Distribution directory found, remove.
	rm -r $distDir
fi

# Create dist directory
mkdir $distDir

echo "Compiling . . . "
# Compile source code
handle $srcDir


echo "Copying . . . "
# Add data to distribution
for i in $(ls $dataDir); do
	cp $dataDir/$i $distDir/$i
done

echo "Archiving . . . "
cd $distDir
jar cf $finalName.jar *
cd ..

echo "Done . . . "
exit 0