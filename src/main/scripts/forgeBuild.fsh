# JBoss Forge 3.x Script
# Requires AngularJS addon. Otherwise it will be
# To execute first time:  addon-install --coordinate org.jboss.forge.addon:angularjs
#
project-new --named bookstoreForge --top-level-package com.oltruong.bookstore --build-system JAVA_EE_7

jpa-setup
constraint-setup
rest-setup


jpa-new-entity --named Book

jpa-new-field --named name
jpa-new-field --named description
jpa-new-field --named isbn
jpa-new-field --named pictureURL
jpa-new-field --named author
jpa-new-field --named price --type float
jpa-new-field --named creationDate --type java.util.Date

constraint-add --on-property name --constraint NotNull
constraint-add --on-property isbn --constraint Size --max 13
constraint-add --on-property price --constraint Min --value 0
constraint-add --on-property pictureURL --constraint Size --max 255


jpa-new-entity --named OrderLine

jpa-new-field --named quantity --type int
jpa-new-field --named book --type com.oltruong.bookstore.model.Book --relationship-type One-to-One

constraint-add --on-property book --constraint NotNull

jpa-new-entity --named Order --tableName orderTable

jpa-new-field --named name
jpa-new-field --named address
jpa-new-field --named creationDate --type java.util.Date
jpa-new-field --named totalOrder --type float
jpa-new-field --named orderLines --type com.oltruong.bookstore.model.OrderLine --relationship-type One-to-Many

constraint-add --on-property name --constraint NotNull
constraint-add --on-property orderLines --constraint NotNull


rest-generate-endpoints-from-entities --targets *.model.*
scaffold-generate --provider AngularJS --targets *.model.*

build