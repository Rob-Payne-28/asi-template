import axios from "axios";
import {Team} from "./Team";

export async function createTeam(teamName: string): Promise<Team> {
  return (await axios.post("/team", teamName, {headers: {'Content-Type': 'application/json'}})).data
}

export async function getTeams(): Promise<Team[]> {
  return (await axios.get<Team[]>("/teams")).data
}
