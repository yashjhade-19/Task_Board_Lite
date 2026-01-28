import { useEffect, useState } from "react";
import { getTasks } from "../api/taskApi";
import TopBar from "../components/TopBar";
import Board from "../components/Board";
import TaskModal from "../components/TaskModal";
import {
  createTask,
  updateTask,
  deleteTask,
} from "../api/taskApi";




export default function TaskBoard() {
  // -------- core data --------
  const [tasks, setTasks] = useState([]);

  // -------- filters --------
  const [statusFilter, setStatusFilter] = useState("");
  const [priorityFilter, setPriorityFilter] = useState("");
  const [searchText, setSearchText] = useState("");

  // -------- ui states --------
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  // -------- modal state --------
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [selectedTask, setSelectedTask] = useState(null);




  // -------- fetch tasks (later) --------
    useEffect(() => {
    const fetchTasks = async () => {
      try {
        setLoading(true);
        setError(null);
        const data = await getTasks();
        setTasks(data);
      } catch (err) {
        setError("Failed to load tasks");
      } finally {
        setLoading(false);
      }
    };

    fetchTasks();
  }, []);


  const getFilteredTasks = () => {
    return tasks.filter((task) => {
      // ---- status filter ----
      if (statusFilter && task.status !== statusFilter) {
        return false;
      }

      // ---- priority filter ----
      if (priorityFilter && task.priority !== priorityFilter) {
        return false;
      }

      // ---- search filter ----
      if (
        searchText &&
        !task.title.toLowerCase().includes(searchText.toLowerCase())
      ) {
        return false;
      }

      return true;
    });
  };
 const filteredTasks = getFilteredTasks();

   const handleCreateOrUpdate = async (taskData) => {
    try {
      setLoading(true);
      if (selectedTask) {
        await updateTask(selectedTask.id, taskData);
      } else {
        await createTask(taskData);
      }
      const data = await getTasks();
      setTasks(data);
      setIsModalOpen(false);
  setSelectedTask(null);

    } catch {
      setError("Operation failed");
    } finally {
      setLoading(false);
    }
  };

  const handleDelete = async () => {
    if (!selectedTask) return;
    try {
      setLoading(true);
      await deleteTask(selectedTask.id);
      const data = await getTasks();
      setTasks(data);
      setIsModalOpen(false);
      setSelectedTask(null);

    } catch {
      setError("Delete failed");
    } finally {
      setLoading(false);
    }
  };


   return (
    <div className="task-board-container">
      {loading && <p>Loading tasks...</p>}
      {error && <p className="error">{error}</p>}

      {!loading && !error && (
        <>

          <TopBar
  statusFilter={statusFilter}
  setStatusFilter={setStatusFilter}
  priorityFilter={priorityFilter}
  setPriorityFilter={setPriorityFilter}
  searchText={searchText}
  setSearchText={setSearchText}
  onCreateClick={() => {
    setSelectedTask(null);
    setIsModalOpen(true);
  }}
/>

          <Board
  tasks={filteredTasks}
  onTaskClick={(task) => {
    setSelectedTask(task);
    setIsModalOpen(true);
  }}
/>

         <TaskModal
  isOpen={isModalOpen}
  task={selectedTask}
  onClose={() => setIsModalOpen(false)}
  onSave={handleCreateOrUpdate}
  onDelete={handleDelete}
/>

        </>
      )}
    </div>
  );

}
