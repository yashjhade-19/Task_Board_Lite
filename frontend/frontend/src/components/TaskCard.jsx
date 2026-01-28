export default function TaskCard({ task, onClick }) {
  return (
    <div className="task-card" onClick={onClick}>
      <h3 className="task-title">{task.title}</h3>

      <span className={`priority ${task.priority}`}>
        {task.priority}
      </span>

      {task.dueDate && (
        <p className="due-date">Due: {task.dueDate}</p>
      )}
    </div>
  );
}
