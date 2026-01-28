import axios from "axios";

const API_BASE = "http://localhost:8080/api/tasks";

// ---------- READ ----------
export const getTasks = async () => {
  const response = await axios.get(API_BASE);
  return response.data;
};

// ---------- CREATE ----------
export const createTask = async (task) => {
  const response = await axios.post(API_BASE, task);
  return response.data;
};

// ---------- UPDATE (FULL) ----------
export const updateTask = async (id, task) => {
  const response = await axios.put(`${API_BASE}/${id}`, task);
  return response.data;
};

// ---------- UPDATE STATUS ONLY ----------
export const updateTaskStatus = async (id, status) => {
  const response = await axios.patch(`${API_BASE}/${id}/status`, {
    status: status,
  });
  return response.data;
};

// ---------- DELETE ----------
export const deleteTask = async (id) => {
  await axios.delete(`${API_BASE}/${id}`);
};
