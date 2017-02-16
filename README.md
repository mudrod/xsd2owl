# xsd2owl
A tool for generating OWL from XSD and XML.

# Introduction
This tool is used in Mudrod to generate an OWL manifestation of the 
[Directory Interchange Format (DIF) Standard](https://earthdata.nasa.gov/standards/directory-interchange-format-dif-standard), 
a specific set of attributes for describing Earth science data. 
From its conception in the late 1980’s as a way to document and exchange information on scientific data 
to its implementation in the Global Change Master Directory (GCMD), the DIF has evolved to serve the 
user community in the discovery, access and use of Earth science and related data.

# Requirements
 * Java8
 
# Installation
You must first install [Ontmalizer](https://github.com/srdc/ontmalizer.git) as it is not yet available in Maven Central.
```
$ git clone https://github.com/srdc/ontmalizer.git && cd ontmalizer
$ mvn clean install
```
You can then install xsd2owl as follows
```
$ git clone https://github.com/mudrod/xsd2owl.git && cd xsd2owl
$ mvn clean install
```

# License
xsd2owl is licensed permissively under the [Apache License v2.0](https://www.apache.org/licenses/LICENSE-2.0) 
a copy of which is shipped with this source code.
