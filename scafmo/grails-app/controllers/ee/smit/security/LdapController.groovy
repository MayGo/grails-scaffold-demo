package ee.smit.security

import static org.springframework.http.HttpStatus.NOT_FOUND

import org.restapidoc.annotation.RestApiMethod
import org.restapidoc.annotation.RestApiParam
import org.restapidoc.annotation.RestApiParams
import org.restapidoc.annotation.RestApi
import org.restapidoc.annotation.RestApiResponseObject
import org.restapidoc.pojo.RestApiParamType
import org.restapidoc.pojo.RestApiVerb

/**
 *
 * RESTFUL controller for retrieving users, roles, groups and application endpoints
 * @author mart.jarvi - 2014
 *
 */
@RestApi(name = 'Active Directory query services',
        description = 'Methods for retrieving users, roles, groups and application endpoints')
class LdapController {

    LdapService ldapService

    static responseFormats = ['json']

    @RestApiMethod(description = 'Retrieve groups via params', path = '/organizations/{organization}/groups.json',
            verb = RestApiVerb.GET)
    @RestApiParams(params = [
            @RestApiParam(name = 'organization', type = 'string', paramType = RestApiParamType.PATH,
                    description = 'Organization within ministry',
                    allowedvalues = ['SMIT', 'SIM', 'SKA', 'HK', 'PAA', 'PPA']),
            @RestApiParam(name = 'userUID', required = false, type = 'string', paramType = RestApiParamType.QUERY,
                    description = 'The user UID (isikukood)')

    ])
    @RestApiResponseObject(objectIdentifier = 'Map')
    def groups() {

        if (params.userUID) {
            def groups = ldapService.findUserGroups(params.organization, params.userUID)

            if (groups?.size() > 0) {
                def map = [userUID: params.userUID, groups: groups, organization: params.organization]
                respond map
            } else {
                render(text: [:], status: NOT_FOUND)
            }
            return
        }

        def groups = ldapService.getOrganizationGroups(params.organization)

        if (groups?.size() > 0) {
            def map = [organization: params.organization, groups: groups]
            respond map
        } else {
            render(text: [:], status: NOT_FOUND)
        }
    }

    @RestApiMethod(description = 'Retrieve users via params', path = '/organizations/{organization}/users.json',
            verb = RestApiVerb.GET)
    @RestApiParams(params = [
            @RestApiParam(name = 'organization', type = 'string', paramType = RestApiParamType.PATH,
                    description = 'Organization within ministry',
                    allowedvalues = ['SMIT', 'SIM', 'SKA', 'HK', 'PAA', 'PPA']),
            @RestApiParam(name = 'lastname', required = false, type = 'string', paramType = RestApiParamType.QUERY,
                    description = 'The user lastname'),
            @RestApiParam(name = 'firstname', required = false, type = 'string', paramType = RestApiParamType.QUERY,
                    description = 'The user firstname'),
            @RestApiParam(name = 'email', required = false, type = 'string', paramType = RestApiParamType.QUERY,
                    description = 'The user email'),
            @RestApiParam(name = 'userUID', required = false, type = 'string', paramType = RestApiParamType.QUERY,
                    description = 'The user UID (isikukood)')
    ])
    @RestApiResponseObject(objectIdentifier = 'Map')
    def users() {

        if (!params.organization) {
            render(text: [:], status: NOT_FOUND)
            return
        }

        def users = ldapService.searchUsers(params)

        if (users?.size() > 0) {
            def map = [organization: params.organization, users: users]
            respond map
        } else {
            render(text: [:], status: NOT_FOUND)
        }
    }

    @RestApiMethod(description = 'Retrieve application roles', path = '/applications/{application}/roles.json',
            verb = RestApiVerb.GET)
    @RestApiParams(params = [
            @RestApiParam(name = 'application', type = 'string', paramType = RestApiParamType.PATH,
                    description = 'The application ID in AD, for example: "horizon"'),
            @RestApiParam(name = 'userUID', required = false, type = 'string', paramType = RestApiParamType.QUERY,
                    description = 'The user UID (isikukood) for retrieving only specific user assigned roles')
    ])
    @RestApiResponseObject(objectIdentifier = 'Map')
    def applicationRoles() {
        def roles = ldapService.findApplicationRoles(params.application, params.userUID)
        if (roles?.size() > 0) {
            def map = [application: params.application, roles: roles]
            respond map
        } else {
            render(text: [:], status: NOT_FOUND)
        }
    }

    @RestApiMethod(description = 'Retrieve application endpoint', path = '/applications/{application}/endpoints.json',
            verb = RestApiVerb.GET)
    @RestApiParams(params = [
            @RestApiParam(name = 'application', type = 'string', paramType = RestApiParamType.PATH,
                    description = 'The application ID in AD, for example: "horizon"')
    ])
    @RestApiResponseObject(objectIdentifier = 'Map')
    def applicationEndpoints() {
        def endpoint = ldapService.getApplicationEndPoint(params.application)
        def map = [application: params.application, endpoint: endpoint]
        if (endpoint) {
            respond map
        } else {
            render(text: [:], status: NOT_FOUND)
        }
    }
}
