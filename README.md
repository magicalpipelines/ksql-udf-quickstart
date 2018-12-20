# Overview
This project includes a Maven archetype that can be used for quickly bootstrapping custom KSQL UDFs and UDAFs. Generating
a project from this archetype will produce a couple of example functions (a UDF named `REVERSE` and a UDAF named 
`SUMMARY_STATS`) that are ready to be deployed to your KSQL server. Feel free to use these examples as a starting point
for your own custom KSQL functions.

## Note
This work has been contributed to the official [KSQL project](https://github.com/confluentinc/ksql/pull/2272) and is
awaiting review. I may archive this repository when the related PR gets merged, which means these artifacts will be made available
under a different group ID (likely `io.confluent.ksql`).

# Usage
First, run the following command to generate a new project from this archetype.

```bash
$ mvn archetype:generate -X \
    -DarchetypeGroupId=com.mitchseymour \
    -DarchetypeArtifactId=ksql-udf-quickstart \
    -DarchetypeVersion=0.1.2 \
    -DgroupId=com.mitchseymour.ksql.functions \
    -DartifactId=my-udf \
    -Dversion=0.1.0-SNAPSHOT
```

This will copy an example UDF and UDAF into the `my-udf` directory. Feel free to use these examples to build your own KSQL functions. Once you're ready to deploy your custom functions to a KSQL server, run the following command to build an uber JAR.

```bash
$ mvn clean package
```

Now, all you need to do it copy the JAR to the KSQL ext directory (run `SHOW PROPERTIES` from the KSQL CLI and look for `ksql.extension.dir` if you are unsure where this directory is).

```bash
$ cp target/my-udf-0.1.0-SNAPSHOT.jar /tmp/ext/
```

Finally, run `SHOW FUNCTIONS` from the CLI and you should see your new UDF / UDAF in the list :)

__Note:__ if you'd like to convert the generated project to a Gradle project, run the following command.

```bash
# (optional) convert the generated project to a Gradle project if that is your 
# preferred build system
$ gradle init --type pom
```


# Development workflows
## Install Locally
Install to local maven repo.

```bash
$ export GPG_TTY=$(tty)
$ mvn install
```

## Deploy to Maven Central
First, ensure the sonatype credentials are added to `$HOME/.m2/settings.xml`.

```bash
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
   xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
   xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0
                       https://maven.apache.org/xsd/settings-1.0.0.xsd">
    <servers>
        <server>
            <username>...</username>
            <password>...</password>
            <id>staging</id>
        </server>
        <server>
            <username>...</username>
            <password>...</password>
            <id>snapshots</id>
        </server>
  </servers>
</settings>
```

Then, simply run:

```bash
$ mvn deploy
```

Releases will be uploaded to [Maven Central](https://oss.sonatype.org/content/repositories/staging/com/mitchseymour/ksql-udf-quickstart/).
