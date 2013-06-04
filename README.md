Texen Gradle Plugin
====================

This plugin for Gradle allows you to generate all sort of text output. To use it, simply include the required JARs via `buildscript {}` and 'apply' the plugin:

### Quickstart
I provided a sample here on GitHub. Check it out.
**[TexenPlugin Sample](https://github.com/MikeMitterer/gradle-texen-plugin-sample)** 

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
	 configurations {
		 velocityAntTask
	 }
	 
	 dependencies {
		 velocityAntTask group: 'org.apache.velocity', 	name: 'velocity', 			version: '1.7'
		 velocityAntTask group: 'commons-lang', 		name: 'commons-lang', 		version: '2.6'
		 velocityAntTask group: 'commons-collections', 	name: 'commons-collections',version: '3.2.1'
	 }
	
	
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

If this plugin is helpful for you - please [(Circle)](http://gplus.mikemitterer.at/) me.