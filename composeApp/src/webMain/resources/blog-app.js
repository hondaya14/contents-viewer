import React, { useEffect, useMemo, useState } from 'https://esm.sh/react@18.3.1';
import { createRoot } from 'https://esm.sh/react-dom@18.3.1/client';
import ReactMarkdown from 'https://esm.sh/react-markdown@9.0.3?deps=react@18.3.1';
import { Prism as SyntaxHighlighter } from 'https://esm.sh/react-syntax-highlighter@15.5.0?deps=react@18.3.1';
import { oneDark } from 'https://esm.sh/react-syntax-highlighter@15.5.0/dist/esm/styles/prism?deps=react@18.3.1';

const CodeBlock = ({ inline, className, children, ...props }) => {
  const match = /language-(\w+)/.exec(className || '');
  if (!inline && match) {
    return (
      <SyntaxHighlighter
        style={oneDark}
        language={match[1]}
        PreTag="div"
        customStyle={{ margin: '1.5rem 0', borderRadius: '12px', fontSize: '0.95rem' }}
        {...props}
      >
        {String(children).replace(/\n$/, '')}
      </SyntaxHighlighter>
    );
  }

  return (
    <code className={`inline-code ${className || ''}`} {...props}>
      {children}
    </code>
  );
};

const BlogPost = ({ source }) => {
  const [content, setContent] = useState('');
  const [error, setError] = useState(null);

  useEffect(() => {
    fetch(source)
      .then((res) => {
        if (!res.ok) {
          throw new Error('Failed to load blog content');
        }
        return res.text();
      })
      .then(setContent)
      .catch((err) => setError(err.message));
  }, [source]);

  const components = useMemo(
    () => ({
      code: CodeBlock,
    }),
    []
  );

  if (error) {
    return <p role="alert">{error}</p>;
  }

  if (!content) {
    return <p>Loading article...</p>;
  }

  return (
    <div className="blog-post">
      <ReactMarkdown components={components}>{content}</ReactMarkdown>
    </div>
  );
};

const App = () => (
  <main className="blog-shell">
    <header className="blog-header">
      <p>ブログ</p>
      <h1>Compose × React Markdown</h1>
      <p>Markdown (MDX) をソースとして記事を描画し、コードは syntax highlight 付きで表示します。</p>
    </header>
    <BlogPost source="blog/hello-world.mdx" />
  </main>
);

const container = document.getElementById('blog-root');
if (container) {
  createRoot(container).render(<App />);
}
