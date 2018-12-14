# ksql-archetypes
Maven archetypes of KSQL UDFs / UDAFs

# Install Locally
Install to local maven repo.

```bash
$ mvn install
```

# Deploy to Maven Central
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

__Note:__ if you are deploying a non-SNAPSHOT version, you'll need to visit [the staging repository](https://oss.sonatype.org/#stagingRepositories), find the newly uploaded artifact at the bottom of the list, and select `Close` and then `Release`.

# Usage
First, run the following command to generate a new project from this archetype.

```bash
$ mvn archetype:generate -X \
    -DarchetypeGroupId=com.mitchseymour \
    -DarchetypeArtifactId=ksql-udf-quickstart \
    -DarchetypeVersion=0.1.1 \
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
