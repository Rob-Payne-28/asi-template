import React from "react";
import {render, screen} from "@testing-library/react";
import App from "../App";
import userEvent from "@testing-library/user-event";
import {when} from "jest-when";
import {createPerson, createTeam, getTeams} from "../teamsApiClient";

jest.mock("../teamsApiClient");

const getTeamsApiClient = getTeams as jest.MockedFunction<typeof getTeams>;

function renderPage() {
  getTeamsApiClient.mockResolvedValueOnce([]);
  render(<App/>);
}

describe("Teams Page", () => {
  describe("when the page loads", () => {
    it("displays requested teams from the api", async () => {
      getTeamsApiClient.mockResolvedValue([{"id":1,"name":"Team 1", "members": []},{"id":2,"name":"Team 2", "members": []}]);

      render(<App/>);

      const listItems = await screen.findAllByRole("listitem");
      expect(listItems[0].innerHTML).toContain("Team 1");
      expect(listItems[1].innerHTML).toContain("Team 2");
    });
  });

  describe("creating", () => {
    it("appends the new team name to the list of teams", async () => {
      when(createTeam).calledWith("Team 1").mockResolvedValueOnce({"id":1,"name":"Team 1", "members": []});

      renderPage();

      getTeamsApiClient.mockResolvedValueOnce([{"id":1,"name":"Team 1", "members": []}]);
      userEvent.type(await screen.findByLabelText("Team Name"), "Team 1");
      userEvent.click(screen.getByRole("button", {name: /create team/i}));
      expect(await screen.findByText("Team 1")).toBeInTheDocument();
    });

    it("assigns new people to unallocated team", async () => {
      renderPage()

      when(createPerson).calledWith("New Person").mockResolvedValueOnce({"id": 1, "name": "New Person"});

      getTeamsApiClient.mockResolvedValue([{"id":1,"name":"Unallocated","members": [{"id": 1, "name": "New Person"}]}]);
      userEvent.type(screen.getByLabelText("Person Name"), "New Person");
      userEvent.click(screen.getByRole("button", {name: /create person/i}));
      await screen.findByText("Unallocated");
      expect(await screen.findByText("New Person")).toBeInTheDocument();
    });
  });
});
