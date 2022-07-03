import React, { useEffect, useState } from 'react';
import { BlogService, Post } from '../../shared/services/api/blog';

export function Blog() {

  const [posts, setPosts] = useState([] as Post[])
  const [loading, setLoading] = useState(true)

  useEffect(() => {
    BlogService.getAll()
      .then((result) => {
        if (result instanceof Error) {
          alert(result.message);

        } else {
          console.log(result);
          setPosts(result.data)
          setLoading(false)
        }
      });
  }, []);


  return (
    <div>
      <h1>Blog</h1>

      {!loading && (
        <ul>
          {posts.map((post) => (
            <li key={post.id+""}>
              Post: {post.id+""} <strong>{post.title}</strong>
            </li>
          ))}
        </ul>
      )}

    </div>
  );
}
