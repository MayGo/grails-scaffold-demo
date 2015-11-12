angular.module('angularDemoApp').factory('MenuService',
  function ($state, $rootScope, $http, $window) {

    var version = {};

    var sections = [{
      name: 'menu.static.DASHBOARD',
      link: 'app.dashboard',
      icon: 'fa-dashboard',
      type: 'link'
    }];


    var menu_pomodoro = [];

    menu_pomodoro.push({
      name: 'menu.domain.tag',
      link: 'app.tag.list',
      icon: 'glyphicon-cloud',
      type: 'link'
    });


    menu_pomodoro.push({
      name: 'menu.domain.task',
      link: 'app.task.list',
      icon: 'glyphicon-cloud',
      type: 'link'
    });


    sections.push({
      name: 'menu.package.pomodoro',
      icon: 'glyphicon-cloud',
      pages: menu_pomodoro.sort(sortByName),
      type: 'toggle'
    });

    var menu_samples = [];

    menu_samples.push({
      name: 'menu.domain.owner',
      link: 'app.owner.list',
      icon: 'glyphicon-envelope',
      type: 'link'
    });


    menu_samples.push({
      name: 'menu.domain.person',
      link: 'app.person.list',
      icon: 'glyphicon-envelope',
      type: 'link'
    });


    menu_samples.push({
      name: 'menu.domain.pet',
      link: 'app.pet.list',
      icon: 'glyphicon-envelope',
      type: 'link'
    });


    menu_samples.push({
      name: 'menu.domain.petType',
      link: 'app.petType.list',
      icon: 'glyphicon-envelope',
      type: 'link'
    });


    menu_samples.push({
      name: 'menu.domain.speciality',
      link: 'app.speciality.list',
      icon: 'glyphicon-envelope',
      type: 'link'
    });


    menu_samples.push({
      name: 'menu.domain.vet',
      link: 'app.vet.list',
      icon: 'glyphicon-envelope',
      type: 'link'
    });


    menu_samples.push({
      name: 'menu.domain.visit',
      link: 'app.visit.list',
      icon: 'glyphicon-envelope',
      type: 'link'
    });


    sections.push({
      name: 'menu.package.samples',
      icon: 'glyphicon-envelope',
      pages: menu_samples.sort(sortByName),
      type: 'toggle'
    });

    var menu_collection = [];

    menu_collection.push({
      name: 'menu.domain.divisionCollection',
      link: 'app.divisionCollection.list',
      icon: 'glyphicon-glass',
      type: 'link'
    });


    menu_collection.push({
      name: 'menu.domain.divisionCollectionless',
      link: 'app.divisionCollectionless.list',
      icon: 'glyphicon-glass',
      type: 'link'
    });


    menu_collection.push({
      name: 'menu.domain.personCollection',
      link: 'app.personCollection.list',
      icon: 'glyphicon-glass',
      type: 'link'
    });


    menu_collection.push({
      name: 'menu.domain.personCollectionless',
      link: 'app.personCollectionless.list',
      icon: 'glyphicon-glass',
      type: 'link'
    });


    sections.push({
      name: 'menu.package.collection',
      icon: 'glyphicon-glass',
      pages: menu_collection.sort(sortByName),
      type: 'toggle'
    });

    var menu_constr = [];

    menu_constr.push({
      name: 'menu.domain.testNumber',
      link: 'app.testNumber.list',
      icon: 'glyphicon-music',
      type: 'link'
    });


    menu_constr.push({
      name: 'menu.domain.testOther',
      link: 'app.testOther.list',
      icon: 'glyphicon-music',
      type: 'link'
    });


    menu_constr.push({
      name: 'menu.domain.testString',
      link: 'app.testString.list',
      icon: 'glyphicon-music',
      type: 'link'
    });


    sections.push({
      name: 'menu.package.constr',
      icon: 'glyphicon-music',
      pages: menu_constr.sort(sortByName),
      type: 'toggle'
    });

    var menu_embedded = [];

    menu_embedded.push({
      name: 'menu.domain.embed',
      link: 'app.embed.list',
      icon: 'glyphicon-user',
      type: 'link'
    });


    menu_embedded.push({
      name: 'menu.domain.embeddable',
      link: 'app.embeddable.list',
      icon: 'glyphicon-user',
      type: 'link'
    });


    sections.push({
      name: 'menu.package.embedded',
      icon: 'glyphicon-user',
      pages: menu_embedded.sort(sortByName),
      type: 'toggle'
    });

    var menu_security = [];

    menu_security.push({
      name: 'menu.domain.role',
      link: 'app.role.list',
      icon: 'glyphicon-film',
      type: 'link'
    });


    menu_security.push({
      name: 'menu.domain.user',
      link: 'app.user.list',
      icon: 'glyphicon-film',
      type: 'link'
    });


    menu_security.push({
      name: 'menu.domain.userRole',
      link: 'app.userRole.list',
      icon: 'glyphicon-film',
      type: 'link'
    });


    sections.push({
      name: 'menu.package.security',
      icon: 'glyphicon-film',
      pages: menu_security.sort(sortByName),
      type: 'toggle'
    });

    var menu_test = [];

    menu_test.push({
      name: 'menu.domain.classifier',
      link: 'app.classifier.list',
      icon: 'glyphicon-th-large',
      type: 'link'
    });


    sections.push({
      name: 'menu.package.test',
      icon: 'glyphicon-th-large',
      pages: menu_test.sort(sortByName),
      type: 'toggle'
    });


    sections.push();

    function sortByName(a, b) {
      return a.name < b.name ? -1 : 1;
    }

    var self = {
      version: version,
      sections: sections,

      selectSection: function (section) {
        self.openedSection = section;
      },
      toggleSelectSection: function (section) {
        self.openedSection = (self.openedSection === section ? null : section);
      },
      isSectionSelected: function (section) {
        return self.openedSection === section;
      },

      selectPage: function (section, page) {
        self.currentSection = section;
        self.currentPage = page;
      },
      isPageSelected: function (page) {
        return self.currentPage === page;
      }
    };

    $rootScope.$on('$stateChangeSuccess', onLocationChange);
    onLocationChange(null, $state.$current);

    return self;

    function onLocationChange(event, toState, toParams, fromState, fromParams) {

      var path = toState.name;
      var settingsLink = {
        name: 'pages.settings.view.title',
        link: 'app.settings',
        type: 'link'
      };

      if (path == settingsLink.link) {
        self.selectSection(settingsLink);
        self.selectPage(settingsLink, settingsLink);
        return;
      }

      var matchPage = function (section, page) {
        if (path === page.link) {
          self.selectSection(section);
          self.selectPage(section, page);
        }
      };

      sections.forEach(function (section) {
        if (section.children) {
          // matches nested section toggles, such as API or Customization
          section.children.forEach(function (childSection) {
            if (childSection.pages) {
              childSection.pages.forEach(function (page) {
                matchPage(childSection, page);
              });
            }
          });
        }
        else if (section.pages) {
          // matches top-level section toggles, such as Demos
          section.pages.forEach(function (page) {
            matchPage(section, page);
          });
        }
        else if (section.type === 'link') {
          // matches top-level links, such as "Getting Started"
          matchPage(section, section);
        }
      });
    }
  });
