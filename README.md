# ksql-archetypes
Maven archetypes of KSQL UDFs / UDAFs

# To Do
- Add UDF / UDAF examples
- Publish to Maven central

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

```bash
$ mvn archetype:generate -X \
    -DarchetypeGroupId=com.mitchseymour \
    -DarchetypeArtifactId=ksql-udf-quickstart \
    -DarchetypeVersion=0.1.0-SNAPSHOT \
    -DgroupId=com.mitchseymour.archetypes \
    -DartifactId=synthwave-name \
    -Dversion=0.1.0-SNAPSHOT
```
