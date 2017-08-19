/**
 *  Capabilities Inspector
 *
 *  Copyright 2017 Travis Illig
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License. You may obtain a copy of the License at:
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 *  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 *  for the specific language governing permissions and limitations under the License.
 *
 */
definition(
    name: "Capabilities Inspector",
    namespace: "tillig",
    author: "Travis Illig",
    description: "Application to log capabilities of a device for easier discovery.",
    category: "My Apps",
    iconUrl: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience.png",
    iconX2Url: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience@2x.png",
    iconX3Url: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience@2x.png")


preferences {
	section("Select Device") {
		input("theDevice", "capability.switch", required: true)
	}
}

def installed() {
	log.debug "Installed with settings: ${settings}"

	initialize()
}

def updated() {
	log.debug "Updated with settings: ${settings}"

	unsubscribe()
	initialize()
}

def initialize() {
	def supportedCaps = theDevice.capabilities
    supportedCaps.each { cap ->
    	log.debug "This device supports the ${cap.name} capability"
        cap.attributes.each {attr ->
            log.debug "-- ${cap.name} Attribute: ${attr.name}"
        }
        cap.commands.each {cmd ->
            log.debug "-- ${cap.name} Command: ${cmd.name}"
        }
	}
}

// TODO: implement event handlers