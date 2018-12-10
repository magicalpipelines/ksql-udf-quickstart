# ksql-archetypes
Maven archetypes of KSQL UDFs / UDAFs

# To Do
- Add UDF / UDAF examples
- Publish to Maven central

# Install
Install to local maven repo.

```bash
$ mvn install
```

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
