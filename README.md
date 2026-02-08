[![Build Status](https://github.com/mP1/j2cl-java-util-timezone-ZoneRulesReader/workflows/build.yaml/badge.svg)](https://github.com/mP1/j2cl-java-util-timezone-ZoneRulesReader/actions/workflows/build.yaml/badge.svg)
[![Coverage Status](https://coveralls.io/repos/github/mP1/j2cl-java-util-timezone-ZoneRulesReader/badge.svg)](https://coveralls.io/github/mP1/j2cl-java-util-timezone-ZoneRulesReader)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![Language grade: Java](https://img.shields.io/lgtm/grade/java/g/mP1/j2cl-java-util-timezone-ZoneRulesReader.svg?logo=lgtm&logoWidth=18)](https://lgtm.com/projects/g/mP1/j2cl-java-util-timezone-ZoneRulesReader/context:java)
[![Total alerts](https://img.shields.io/lgtm/alerts/g/mP1/j2cl-java-util-timezone-ZoneRulesReader.svg?logo=lgtm&logoWidth=18)](https://lgtm.com/projects/g/mP1/j2cl-java-util-timezone-ZoneRulesReader/alerts/)
![](https://tokei.rs/b1/github/mP1/j2cl-java-util-timezone-ZoneRulesReader)
[![J2CL compatible](https://img.shields.io/badge/J2CL-compatible-brightgreen.svg)](https://github.com/mP1/j2cl-central)



# java.util.timezone.timezone.ZoneRulesReader

Provides support for reading the select time zone data for [java.util.TimeZone](https://github.com/mP1/j2cl-java-util-TimeZone) using an cut down [org.threeten](https://github.com/ThreeTen/threetenbp).

Note this project includes two copies of bp 310, shaded into different packages.

- The first at `walkingkooka.j2cl.java.util.timezone.zonerulesreader.org.threeten.bp` contains a few classes copied
  [j2cl-java-time](https://github.com/mP1/j2cl-java-time). These classes are used in unit tests to verify that the extracted
  can be used to initialize the emulatd java.time.
- The second at `walkingkooka.j2cl.java.util.timezone.zonerulesreader.org.threeten.bp.zone` is a complete unmodified
  backport 310 with one small change. The `ZoneRules#writeExternal` method has been made public. This method will be used to
  serialize a provided TimeZone. The APT passes a `java.util.ZoneId` that will be serialized and used as data by GWT/J2CL 
  for the emulated `java.time` from [j2cl-java-time](https://github.com/mP1/j2cl-java-time).

