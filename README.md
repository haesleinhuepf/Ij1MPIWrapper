# Parallel-Macro
This is a project that enables Fiji (an ImageJ distribution) users to parallelize their Macro scripts.

This project is to be used along with HPC Workflow Manager that provides a GUI.
It can be found here: https://github.com/fiji-hpc/hpc-workflow-manager 

There are two Fiji installations needed:
* One on the local system which runs the HPC Workflow Manager client.
* Another located at the target system, a computer-cluster.

## Build 
Use [maven](https://maven.apache.org/) with package target to build a jar of this project.

From the command line this can be done like that:
```
git clone https://github.com/MKrumnikl/Ij1MPIWrapper.git
cd Ij1MPIWrapper
mvn package
```

## Install
Before building this package you must:
* Make sure that OpenMPI is installed (or available as a module) with the Java bindings configured on your target system.

Steps for installation:
* Copy the jar file of the project that you build;
* download and install Fiji on the target system (if it is not already installed);
* paste the project's jar file in Fiji's either plugins or jars directory (any one of the two directories).

You can copy the jar file into your Fiji installation with a command like this:
```
cp target/ParallelMacro-0.0.1-SNAPSHOT.jar path/to/Fiji.app/jars/target/ParallelMacro-0.0.1-SNAPSHOT.jar
```

You can copy it to the HPC cluster like with [zip](https://linux.die.net/man/1/zip) and [scp](https://linux.die.net/man/1/scp). If you work on Windows, you can do this e.g. via the [Windows Subsystem for Linux](https://docs.microsoft.com/en-us/windows/wsl/install-win10):
```
zip -r Fiji.app.zip path/to/Fiji.app/
scp Fiji.app.zip <username>@<servername>:/home/<username>/
```

After logging into the cluster, [unzip](https://linux.die.net/man/1/unzip) the fiji installation:
```
unzip Fiji.app.zip
```

### If it does not work:
Make sure that the directory mpi.jar is in the $LD\_LIBRARY\_PATH of the target system.
Parallel-Macro will find the mpi.jar automatically in the directories listed in the environment variable.
It will also try to "ml" or "module load" the OpenMPI 4.0 module before looking in the environment variable.

You will need to repeat the instructions for every target system.

If you need to manually add the path of mpi.jar in $LD\_LIBRARY\_PATH it is located in OpenMPI's lib directory.

For example, this plugin was tested on the Salomon supercomputer.
The mpi.jar on Salomon is located at /apps/all/OpenMPI/4.0.0-GCC-6.3.0-2.27/lib
