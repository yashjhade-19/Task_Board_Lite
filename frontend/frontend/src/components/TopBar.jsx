export default function TopBar({
  statusFilter,
  setStatusFilter,
  priorityFilter,
  setPriorityFilter,
  searchText,
  setSearchText,
  onCreateClick,
}) {
  return (
    <div className="top-bar">
      <h1 className="page-title">Task Board</h1>

      <div className="filters">
        {/* Status Filter */}
        <select
          value={statusFilter}
          onChange={(e) => setStatusFilter(e.target.value)}
        >
          <option value="">All Status</option>
          <option value="TODO">To Do</option>
          <option value="IN_PROGRESS">In Progress</option>
          <option value="DONE">Done</option>
        </select>

        {/* Priority Filter */}
        <select
          value={priorityFilter}
          onChange={(e) => setPriorityFilter(e.target.value)}
        >
          <option value="">All Priority</option>
          <option value="LOW">Low</option>
          <option value="MEDIUM">Medium</option>
          <option value="HIGH">High</option>
        </select>

        {/* Search (optional bonus) */}
        <input
          type="text"
          placeholder="Search tasks..."
          value={searchText}
          onChange={(e) => setSearchText(e.target.value)}
        />

        {/* Create Task Button */}
        <button className="primary-btn" onClick={onCreateClick}>
          Create Task
        </button>
      </div>
    </div>
  );
}
