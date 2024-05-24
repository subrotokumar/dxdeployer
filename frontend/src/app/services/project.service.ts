import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../enviroments/environment.prod';


@Injectable({
  providedIn: 'root'
})
export class ProjectService {

  constructor(private http: HttpClient) { }

  findAllProjects = () => {
    const accessToken = localStorage.getItem("access_token")
    this.http.get(
      environment.projectService,
      {
        headers: {
          'Authorization': `Bearer ${accessToken}`
        }
      }
    )
  }

  findProjectById = ({projectId}: {projectId: number}) => {
    const accessToken = localStorage.getItem("access_token")
    this.http.get(
      `${environment.projectService}/${projectId}`,
      {
        headers: {
          'Authorization': `Bearer ${accessToken}`
        }
      }
    )
  }

  deleteProject = ({projectId}: {projectId: number}) => {
    const accessToken = localStorage.getItem("access_token")
    this.http.get(
      `${environment.projectService}/${projectId}`,
      {
        headers: {
          'Authorization': `Bearer ${accessToken}`
        }
      }
    )
  }

}
