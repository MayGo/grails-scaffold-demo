
angular.module('angularDemoApp').run(['$templateCache', function($templateCache) {
	$templateCache.put('testStringAutocompleteCustomTemplate.html',
			'<ul tabindex="-1" class="typeahead dropdown-menu" ng-show="$isVisible()" role="select">\
				<li role="presentation" ng-repeat="match in $matches" ng-class="{active: $index == $activeIndex}">\
					<a role="menuitem" tabindex="-1" ng-click="$select($index, $event)" ng-bind-html="autocompleteService.testStringSimpleFormatLabel(match)"></a>\
				</li>\
			</ul>'
	);
}]);
