import { Api } from "../axios-config";

export interface Post {
    user: Number;
    id: Number;
    title: String;
    body: String;
}

export interface PostCollection {
    data: Post[];
    total: Number;
}


const getAll = async(): Promise<PostCollection | Error>  => {
    try {
        const { data } = await Api.get('/posts');
        if (data) {
            return {
                data,
                total: data.length
            };
        }
        return new Error('Erro ao listar');
    } catch (error) {
        return new Error('Erro: '+error);
    }
}

export const BlogService = {
    getAll
};