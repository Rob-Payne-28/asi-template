describe("teams", () => {
  beforeEach(() => {
      cy.visit("http://localhost:8080");
  });

  describe("creating a team", () => {
    function addTeam(team: string) {
      cy.findByLabelText("Team Name").type(team);
      cy.findByRole("button", {name: "Create Team"}).click();
    }
    function addPerson(team: string) {
      cy.findByLabelText("Person Name").type(team);
      cy.findByRole("button", {name: "Create Person"}).click();
    }

    it("updates the displayed list of teams", () => {
      addTeam("our-team-name");
      cy.findByText("our-team-name").should("exist");
    });

    it("automatically creates an unallocated team", () => {
      cy.findByText(/unallocated/i);
    });

    it("adds newly created people to the unallocated team", () => {
      addPerson("our-person-name");
      cy.findByText("our-person-name").should("exist");
    });
  });
});
