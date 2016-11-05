/* Copyright 2013-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package grailsdemo.sec

import grails.core.GrailsApplication
import grails.plugin.springsecurity.SpringSecurityUtils
import grails.plugin.springsecurity.shiro.ShiroPermissionResolver

/**
 * @author <a href='mailto:burt@burtbeckwith.com'>Burt Beckwith</a>
 * @author <a href='mailto:'>Michael Schmidt</a>
 */
class UserRoleShiroPermissionResolver implements ShiroPermissionResolver {

	GrailsApplication grailsApplication

	Set<String> resolvePermissions(String username) {

		def conf = SpringSecurityUtils.securityConfig

		String permissionClassName = conf.shiro.permissionDomainClassName
		if (!permissionClassName) {
			throw new RuntimeException('No value specified for the Shiro permission class; ' +
				'set the grails.plugin.springsecurity.shiro.permissionDomainClassName attribute')
		}

		def dc = grailsApplication.getDomainClass(permissionClassName)
		if (!dc) {
			throw new RuntimeException("The specified permission domain class '$permissionClassName' is not a domain class")
		}

		Class<?> Permission = dc.clazz
		String usernamePropertyName = conf.userLookup.usernamePropertyName
		def permissions = []
		Permission.withCriteria {
			user {
				eq usernamePropertyName, username
			}
			projections {
				property 'permission'
			}
		}.each {
			permissions << it
		}

		return permissions
	}
}
