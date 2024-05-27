export interface Project {
    id: number;
    slug: string;
    title: string;
    description: string;
    githubUrl:string;
    status: string;
    type: string;
    createdAt: string;
    userId: number;
    // lastDeployedAt?: string;
    tags: string[]
}