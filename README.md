Texen Gradle Plugin
====================

This plugin for Gradle allows you to generate all sort of text output.<br>
To use it, simply include the required JARs via `buildscript {}` and 'apply' the plugin

### Quickstart
I provided a sample here on GitHub. Check it out.
*	**[TexenPlugin Sample](https://github.com/MikeMitterer/gradle-texen-plugin-sample)** 
*	**[What is Texen](http://velocity.apache.org/engine/devel/texen.html)**

Generate SQL in Sample (build/database/out)

	./gradlew texen
	
Write property file + generate SQL (build/database/out)

	./gradlew test2
	
Video shows how the plugin works

*	**[Screencast](http://www.mikemitterer.at/infopoint/programmierung/texen-gradle-plugin.html)**
	
### Configuration
	buildscript {
	    repositories {
			// My local repository, I use this for testing...
	        //maven { url uri("/Volumes/programming/Maven/public.maven.repository") } 
			
			// My public GitHub Maven repository
	        maven { url uri("https://raw.github.com/MikeMitterer/public.maven.repository/master") }  
	                         
	    }
	    dependencies {
	        classpath group: 'at.mikemitterer.gradle', name: 'TexenPlugin', version: '1.2'
	    }
	}
	
	apply plugin: 'texen'

	repositories{
	    mavenCentral() // Use 'maven central' for resolving your thirdparty dependencies.
		
		maven {
			url uri("https://raw.github.com/MikeMitterer/public.maven.repository/master")
		}
	}


	/*-------------------------------------------------------------------------------------------------
	 * Configuration necessary for TexenPlugin
	 */
	
	texenplugin {
	// Default settings for texenplugin:
	//-----------------------------------
		//baseDir			= "build/database"
		//outputDir			= "out"
		//templateDir		= "templates"
		//controlTemplate 	= "control.vm"
		//reportFile		= "generation.report"
		//deleteReport 		= false
		
		deleteReport 	= true
	}
	
License
========

    Copyright 2012 Michael Mitterer, IT-Consulting and Development Limited,
    Austrian Branch

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, 
    software distributed under the License is distributed on an 
    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, 
    either express or implied. See the License for the specific language 
    governing permissions and limitations under the License.
    
If this plugin is helpful for you - please [(Circle)](http://gplus.mikemitterer.at/) me.