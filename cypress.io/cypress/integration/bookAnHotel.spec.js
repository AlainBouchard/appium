describe(
  'Hotel use case', () => {
    it('go from landing page to calendar', () => {
      const URL_PATH = '/';
      cy.visit(URL_PATH);

      cy
        .get('.booking-category-button-desktop')  // TODO: Open a JIRA-#### to add test-id attribute (best practices)
        .contains('Hotels')
        .click();

      cy
        .intercept('**/autocomplete/lodging')
        .as('autocompleteLodging');

      cy
        .get('input[name="terminus-input"]')
        .click()
        .type('Buenos Aires')
        .wait('@autocompleteLodging');

      cy
        .get('.MuiAutocomplete-groupUl')
        .find('.MuiAutocomplete-option')
        .contains('Buenos Aires, Argentina')
        .click();

      cy
        .get('input[placeholder="Check-in"]')
        .click();

      cy
        .get('.calendar-popup-root')
        .should('be.visible');

      cy
        .get('.calendar-popup-root')
        .find('.day-root')
        .not('.disabled').then((date) => {
          cy.wrap(date[0]).click();
          cy.wrap(date[1]).click();
        });

      cy
        .get('.calendar-popup-root')
        .find('button')
        .contains('Done')
        .click();

      cy
        .intercept('**/hotel/availability')
        .as('availabilityList');

      cy
        .get('.hotel-search')
        .find('button')
        .contains('Search')
        .click()
        .wait('@availabilityList', {timeout: 10000});

      cy
        .get('.lodging-availability-contents-list')
        .find('.availability-row')
        .its('length')
        .should('be.gte', 1);
    })
  }
)