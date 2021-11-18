Cypress.Commands.add('waitForAvailabilities', (minimum) => {
  minimum = minimum || 1;

  cy
    .get('.lodging-availability-contents-list')
    .find('.availability-row')
    .its('length')
    .should('be.gte', minimum);
});
