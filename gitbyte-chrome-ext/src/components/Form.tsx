import React, { useEffect, useState } from 'react';
import './Form.css';

interface Tech {
  id: number;
  name: string;
  version: string;
  techType: string;
}

const Form: React.FC = () => {
  const [techStacks, setTechStacks] = useState<{ [key: string]: Tech[] }>({});
  const [selectedTechs, setSelectedTechs] = useState<number[]>([]);
  const [username, setUsername] = useState<string>('');
  const [accessToken, setAccessToken] = useState<string>('');
  const [repo, setRepo] = useState<string>('');
  const [openDropdowns, setOpenDropdowns] = useState<{ [key: string]: boolean }>({});

  useEffect(() => {
    const fetchTechStacks = async () => {
      const response = await fetch('http://localhost:9090/v1/api/techstack/dropdown', {
        method: 'GET',
        headers: {
          'Content-Type': 'application/json',
        }
      });
      const data = await response.json();
      setTechStacks(data);

      // Initialize all dropdowns to be closed
      const initialOpenDropdowns = Object.keys(data).reduce((acc, techType) => {
        acc[techType] = false;
        return acc;
      }, {} as { [key: string]: boolean });
      setOpenDropdowns(initialOpenDropdowns);
    };

    fetchTechStacks();
  }, []);

  useEffect(() => {
    chrome.runtime.sendMessage({ action: "getTabUrl" }, (response) => {
      if (response?.url) {
        setRepo(response.url);
      }
    });
  }, []);

  const handleTechChange = (techType: string, id: number) => {
    setSelectedTechs((prevSelectedTechs) => 
      prevSelectedTechs.includes(id)
        ? prevSelectedTechs.filter((techId) => techId !== id)
        : [...prevSelectedTechs, id]
    );
  };

  const handleSubmit = async () => {
    if (!accessToken || selectedTechs.length === 0) {
      alert('Access Token and at least one tech selection are required.');
      return;
    }

    const payload = {
      username: username || null,
      accessToken,
      repo,
      techs: selectedTechs,
    };

    const response = await fetch('http://localhost:9090/v1/api/user/configure', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(payload),
    });

    if (response.ok) {
      alert('Configuration successful!');
    } else {
      alert('Configuration failed.');
    }
  };

  const toggleDropdown = (techType: string) => {
    setOpenDropdowns((prevOpenDropdowns) => ({
      ...prevOpenDropdowns,
      [techType]: !prevOpenDropdowns[techType],
    }));
  };

  return (
    <div className="container">
      {Object.keys(techStacks).map((techType) => (
        <div key={techType} className="dropdown-container">
          <h3 onClick={() => toggleDropdown(techType)} className="dropdown-header">
            {techType} {openDropdowns[techType] ? '▲' : '▼'}
          </h3>
          {openDropdowns[techType] && (
            <select
              multiple
              className="dropdown"
              value={selectedTechs
                .filter(techId => techStacks[techType].some(tech => tech.id === techId))
                .map(String)} // Convert selected tech IDs to strings
              onChange={(e) => {
                const options = e.target.options;
                const selected: number[] = [];
                for (let i = 0; i < options.length; i++) {
                  if (options[i].selected) {
                    selected.push(Number(options[i].value)); // Convert selected values back to numbers
                  }
                }
                setSelectedTechs((prevSelectedTechs) => [
                  ...prevSelectedTechs.filter(techId => !techStacks[techType].some(tech => tech.id === techId)),
                  ...selected
                ]);
              }}
            >
              {techStacks[techType].map((tech) => (
                <option key={tech.id} value={tech.id.toString()}>
                  {tech.name} {tech.version}
                </option>
              ))}
            </select>
          )}
        </div>
      ))}
      <input
        type="text"
        placeholder="Username"
        value={username}
        onChange={(e) => setUsername(e.target.value)}
        className="input-field"
      />
      <input
        type="text"
        placeholder="Access Token"
        value={accessToken}
        onChange={(e) => setAccessToken(e.target.value)}
        required
        className="input-field"
      />
      <input
        type="text"
        placeholder="Repository"
        value={repo}
        readOnly
        className="input-field"
      />
      <button onClick={handleSubmit} className="submit-button">Submit</button>
    </div>
  );
};

export default Form;
