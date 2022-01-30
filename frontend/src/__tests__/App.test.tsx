import React from "react";
import {render, screen} from "@testing-library/react";
import App from "../App";
import userEvent from "@testing-library/user-event";
import {when} from "jest-when";
import {createTeam, getTeams} from "../teamsApiClient";

jest.mock("../teamsApiClient");

const getTeamsApiClient = getTeams as jest.MockedFunction<typeof getTeams>;

describe("Teams Page", () => {
  describe("when the page loads", () => {
    it("displays requested teams from the api", async () => {
      getTeamsApiClient.mockResolvedValue([{"id":1,"name":"Team 1"},{"id":2,"name":"Team 2"}]);

      render(<App/>);

      const listItems = await screen.findAllByRole("listitem");
      expect(listItems[0].innerHTML).toEqual("Team 1");
      expect(listItems[1].innerHTML).toEqual("Team 2");
    });
  });

  describe("creating", () => {

    it("appends the team name to the list", async () => {
      when(createTeam).calledWith("Team 1").mockResolvedValueOnce({"id":1,"name":"Team 1"});

      getTeamsApiClient.mockResolvedValueOnce([]);
      getTeamsApiClient.mockResolvedValueOnce([{"id":1,"name":"Team 1"}]);
      render(<App/>);
      expect(screen.queryByText("Team 1")).not.toBeInTheDocument();


      userEvent.type(screen.getByLabelText("Team Name"), "Team 1");
      userEvent.click(screen.getByRole("button", {name: /submit/i}));
      userEvent.clear(screen.getByLabelText("Team Name"));
      expect(await screen.findByText("Team 1")).toBeVisible();
    });
  });
});
