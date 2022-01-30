describe("teams", () => {
  describe("creating a team", () => {
    function addTeam(team: string) {
      cy.findByLabelText("Team Name").type(team);
      cy.findByRole("button").click();
    }

    it("updates the displayed list of teams", () => {
      cy.visit("http://localhost:8080");
      addTeam("our-team-name");
      cy.findByLabelText("Team Name").clear();
      cy.findByText("our-team-name").should("exist");
    });
  });
});
