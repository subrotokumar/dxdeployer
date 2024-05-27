import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../enviroments/environment.prod';
import { ApiResponse } from '../core/types/types';
import { Project } from '../core/types/project.types';
import { ApiService } from './api.service';


@Injectable({
  providedIn: 'root'
})
export class ProjectService {

  constructor(private apiService: ApiService) { }

  findAllProjects = () => {
    const accessToken = localStorage.getItem("access_token")
    return this.apiService.get<HttpResponse<ApiResponse<Array<Project>>>>(
      environment.projectService,
      {
        headers: {
          'Authorization': `Bearer ${accessToken}`
        },
        observe: 'events'
      }
    )
  }

  findProjectById = ({projectId}: {projectId: number}) => {
    const accessToken = localStorage.getItem("access_token")
    return this.apiService.get(
      `${environment.projectService}/${projectId}`,
      {
        headers: {
          'Authorization': `Bearer ${accessToken}`
        },
        observe: 'events'
      }
    )
  }

  deleteProject = ({projectId}: {projectId: number}) => {
    const accessToken = localStorage.getItem("access_token")
    return this.apiService.get(
      `${environment.projectService}/${projectId}`,
      {
        headers: {
          'Authorization': `Bearer ${accessToken}`
        },
        observe: 'events'
      }
    )
  }

}
