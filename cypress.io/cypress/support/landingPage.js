Cypress.Commands.add('clickHotelButton', () => { 
  // TODO: Open a JIRA-#### to add test-id attribute (best practices) and
  // test-id could be the same for mobile screen formats and computer browsers.
  cy
    .get('.booking-category-button-desktop')
    .contains('Hotels')
    .click();
});

Cypress.Commands.add('typeLocation', (location) => { 
  cy
    .intercept('**/autocomplete/lodging')
    .as('autocompleteLodging');

  cy
    .get('input[name="terminus-input"]')
    .click()
    .type(location)
    .wait('@autocompleteLodging');
});

Cypress.Commands.add('selectLocation', (location) => { 
  cy
    .get('.MuiAutocomplete-groupUl')
    .find('.MuiAutocomplete-option')
    .contains(location)
    .click();
});

Cypress.Commands.add('pickDates', (days) => {
  days = days || 1;

  cy
    .get('.calendar-popup-root')
    .should('be.visible');

  cy
    .get('.calendar-popup-root')
    .find('.day-root')
    .not('.disabled').then((dates) => {
      cy.wrap(dates[0]).click();
      cy.wrap(dates[days - 1]).click();
    });
});

Cypress.Commands.add('clickDoneButton', () => {
  cy
    .get('.calendar-popup-root')
    .find('button')
    .contains('Done')
    .click();
});

Cypress.Commands.add('clickSearchButton', () => {
  cy
    .intercept('**/hotel/availability')
    .as('availabilityList');

  cy
    .get('.hotel-search')
    .find('button')
    .contains('Search')
    .click()
    .wait('@availabilityList', {timeout: 10000});
});
