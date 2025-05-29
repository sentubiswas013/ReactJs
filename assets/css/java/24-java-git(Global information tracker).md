# Top 1000 Java Interview Question & Answers

## GIT(Global information tracker)

### 822. **How can we see n most recent commits in GIT?**

You can use the following command to see the **n most recent commits** in Git:

```bash
git log -n <number>
```

For example, to see the **5 most recent commits**, use:

```bash
git log -n 5
```

Alternatively, you can use `git log` with the `--oneline` flag to get a concise, one-line-per-commit output:

```bash
git log --oneline -n <number>
```

For example:

```bash
git log --oneline -n 5
```

This shows the most recent 5 commits in a short format.

---

### 823. **How can we know if a branch is already merged into master in GIT?**

To check if a branch has been merged into `master` (or any other branch), use the following command:

```bash
git branch --merged master
```

This will list all branches that have already been merged into `master`. If the branch you're checking is listed, then it has been merged.

If you want to check if a specific branch, say `feature-branch`, is merged into `master`, use:

```bash
git branch --merged master | grep feature-branch
```

If `feature-branch` is merged, it will show up in the output.

---

### 824. **What is the purpose of `git stash drop`?**

`git stash drop` is used to **delete a specific stash** from the stash list. When you stash changes in Git, they are stored in a stack-like structure. If you no longer need a particular stash, you can use this command to remove it.

For example:

```bash
git stash drop stash@{0}
```

This will drop (delete) the stash at position `0` in the stash list. If you want to remove all stashes, you can use:

```bash
git stash clear
```

This command deletes **all** stashes in the list.

---

### 825. **What is the HEAD in GIT?**

In Git, **HEAD** is a reference to the **current commit** in your working directory. It is a symbolic pointer to the most recent commit on the current branch. It helps Git know what branch or commit you're currently working on.

- When you're on a branch (e.g., `master`), `HEAD` points to the latest commit of that branch.
- When you're in a **detached HEAD state** (e.g., after checking out a specific commit), `HEAD` points directly to that specific commit instead of a branch.

You can see the current state of HEAD using:

```bash
git symbolic-ref HEAD
```

You can also check the current commit that HEAD points to by using:

```bash
git log -1
```

---

### 826. **What is the most popular branching strategy in GIT?**

The most popular and widely used Git branching strategy is **Git Flow**. Git Flow provides a clear, structured approach to branching, especially in teams working on large projects. It uses multiple types of branches for different purposes:

1. **Master Branch**: This branch always holds the production-ready code.
2. **Develop Branch**: This is the integration branch where features are merged after being developed. It represents the latest delivered development changes.
3. **Feature Branches**: These branches are created off `develop` and are used to work on specific features. Once the feature is complete, it is merged back into `develop`.
4. **Release Branches**: These branches are used to prepare for a new production release. They are created from `develop` and contain bug fixes, versioning, and documentation.
5. **Hotfix Branches**: These branches are used to quickly address issues in the `master` (production) branch. They are created from `master` and merged back into both `master` and `develop` once the issue is fixed.

Other popular branching strategies include:

- **GitHub Flow**: A simpler strategy, primarily used for continuous deployment workflows, where each feature or fix is developed in its own branch, tested, and merged directly into `master`.
- **GitLab Flow**: A more flexible approach, often involving environment branches like `staging`, `production`, and `development`.


### 827. **What is SubGit?**

**SubGit** is a tool that allows you to **migrate or synchronize repositories** between Git and Subversion (SVN) without losing history or making manual changes. It enables you to use Git as a frontend while maintaining a Subversion repository backend. SubGit can be used to **mirror** or **migrate** a Subversion repository to Git, making it possible for teams using Git to work with an existing SVN repository.

SubGit ensures that Git commits are synchronized with Subversion and vice versa, so both systems can work together seamlessly. It can be particularly useful for organizations transitioning from SVN to Git while maintaining access to both systems during the migration process.

---

### 828. **What is the use of `git instaweb`?**

`git instaweb` is a command in Git that **sets up a web interface** for a Git repository, allowing you to browse the repository's contents through a web browser. It launches a lightweight web server and provides a local web interface to view your Git repository, including commit logs, branches, and file changes.

By running:

```bash
git instaweb
```

Git will start a web server and provide a URL (typically `http://localhost:8080`) where you can view the repository. It's a quick way to visualize a Git repository without needing a dedicated tool or GUI.

---

### 829. **What are git hooks?**

**Git hooks** are scripts that run automatically at certain points in the Git workflow. These scripts are triggered by Git commands and allow you to **automate tasks** or **enforce policies** at various stages of the development lifecycle.

There are two types of hooks:

1. **Client-side hooks**: These are triggered by actions in your local repository, such as committing, merging, or checking out files.
   - Examples: `pre-commit`, `commit-msg`, `post-commit`, `pre-push`, etc.

2. **Server-side hooks**: These are triggered on the server-side, typically during operations like pushing or receiving changes.
   - Examples: `pre-receive`, `post-receive`, `update`, etc.

Git hooks are located in the `.git/hooks` directory of the repository, and each hook is represented by a script file. These scripts can be written in any language (e.g., bash, Python, Perl) and can be used to enforce best practices, check for coding style, validate commit messages, run tests, and more.

For example, the `pre-commit` hook can be used to run unit tests or check for code quality before a commit is made.

---

### 830. **What is GIT?**

**Git** is a **distributed version control system (VCS)** that tracks changes to files and facilitates collaboration between developers. It allows multiple people to work on the same project concurrently without interfering with each other’s work. Git was created by **Linus Torvalds** in 2005 to support the development of the Linux kernel.

Some key features of Git:
- **Distributed**: Every user has a full copy of the repository, including the entire history, which enables offline work.
- **Version Tracking**: Git tracks changes to files, allowing users to see previous versions, compare differences, and revert to older versions.
- **Branching and Merging**: Git allows users to create branches for isolated work and merge those branches back into the main codebase when work is complete.
- **Fast and Efficient**: Git is optimized for performance, even with large repositories, and provides efficient management of changes.
- **Collaboration**: Git supports multiple developers working on the same project, with features to resolve conflicts when changes are made to the same files.

---

### 831. **What is a repository in GIT?**

A **repository (repo)** in Git is a **directory** that contains all the files, along with the version history of the project. It also stores the information about branches, tags, and other Git-related metadata. A repository can either be **local** or **remote**:

- **Local repository**: This is the version control system on your local machine. It contains all the changes and history specific to your work.
- **Remote repository**: This is typically a central server that stores the main version of the code, and multiple contributors can push and pull changes to/from this repository.

A Git repository contains:
- **Working directory**: The files you are working on.
- **Staging area**: Where changes are prepared before being committed to the repository.
- **Commit history**: A record of all committed changes, tracked in a `.git` directory.

You can initialize a Git repository using:

```bash
git init
```

And you can clone an existing repository using:

```bash
git clone <repository-url>
```

In the case of **remote repositories**, Git supports hosting services like **GitHub**, **GitLab**, and **Bitbucket**, which provide a platform for storing, sharing, and collaborating on repositories.

### 832. **What are the main benefits of GIT?**

The main benefits of Git are:

1. **Distributed Version Control**: Git allows every developer to have a full copy of the repository, including its entire history. This means you can work offline and still have access to the entire project history.

2. **Branching and Merging**: Git makes it easy to create branches, enabling parallel development. Merging changes from different branches is also relatively simple and efficient in Git, which helps in managing multiple features or bug fixes simultaneously.

3. **Performance**: Git is designed to handle large projects efficiently. It’s optimized for performance, allowing fast operations like branching, merging, and committing even for large repositories.

4. **Integrity and Safety**: Git ensures that the history of the repository is maintained in a secure way. Every commit in Git is identified by a unique hash (SHA-1), making it easy to track changes and ensuring the integrity of the codebase.

5. **Flexibility**: Git allows multiple workflows and customization, supporting different branching models and team practices, making it highly adaptable to various projects and environments.

6. **Collaboration**: Git enables seamless collaboration between multiple developers. It supports distributed workflows, where different team members can work independently and later synchronize their changes through merging.

7. **Easy Reverting**: Git allows users to easily revert changes to previous states, whether it's resetting a commit, undoing changes in the working directory, or rolling back to an earlier version.

8. **Staging Area**: Git provides a staging area where you can prepare your commits carefully before finalizing them, giving you more control over your commit history.

---

### 833. **What are the disadvantages of GIT?**

The disadvantages of Git include:

1. **Steep Learning Curve**: Git can be difficult for beginners to understand. Concepts such as branching, merging, rebasing, and the staging area can initially be confusing, especially when compared to simpler version control systems like SVN.

2. **Large Repositories**: Although Git is efficient, very large repositories with lots of history can become slow to clone, fetch, or push, especially when there are large binary files in the project. This can require additional configuration and tools (e.g., Git LFS).

3. **Complex Merge Conflicts**: While Git handles merges efficiently, complex merge conflicts in large teams or with complicated codebases can be difficult to resolve.

4. **Unwanted Changes and Mistakes**: Because Git allows local commits and changes without needing to sync with a central repository, it’s possible to make mistakes or accidentally commit unwanted changes that are hard to trace or fix later.

5. **Git’s CLI Complexity**: While Git is powerful, its command-line interface (CLI) can be intimidating for some developers. Git has many commands, options, and configurations, and some of the commands can be risky if used incorrectly (e.g., `git reset --hard`).

---

### 834. **What are the main differences between GIT and SVN?**

Here are the main differences between Git and SVN (Subversion):

1. **Version Control Type**:
   - **Git**: Distributed version control system (DVCS). Each developer has a full copy of the repository, including the history.
   - **SVN**: Centralized version control system (CVCS). There is a single central repository, and developers commit their changes to it.

2. **Branching and Merging**:
   - **Git**: Branching is lightweight and fast. It’s easy to create, switch, and merge branches without significant overhead.
   - **SVN**: Branching in SVN is more cumbersome and can create overhead, especially in larger repositories.

3. **Offline Working**:
   - **Git**: Since each developer has a full copy of the repository, they can work offline and perform most Git operations without an internet connection.
   - **SVN**: Requires internet access for most operations since there’s only one central repository.

4. **Commit History**:
   - **Git**: Git maintains a complete commit history locally, and every commit is identified by a unique hash.
   - **SVN**: SVN tracks changes on the central server, so developers only have access to the history that exists in the central repository.

5. **Performance**:
   - **Git**: Git is faster for most operations, especially branching, merging, and commits, due to its distributed nature.
   - **SVN**: SVN can be slower for operations like branching and committing, especially in large repositories.

6. **Repository Size**:
   - **Git**: Git can handle large projects more efficiently, especially in terms of speed for common operations. However, it can become slow with large binary files (unless using Git LFS).
   - **SVN**: SVN is better suited for projects with large binary files, as it doesn’t store the full history in every local copy of the repository.

---

### 835. **How will you start GIT for your project?**

To start Git for your project, follow these steps:

1. **Install Git**: If Git is not installed, download and install it from [git-scm.com](https://git-scm.com/).

2. **Initialize a Git Repository**:
   - Navigate to your project directory using the terminal.
   - Run the following command to initialize the Git repository:
     ```bash
     git init
     ```

3. **Add Project Files**:
   - Add files to the staging area with:
     ```bash
     git add .
     ```
     This adds all files in the project directory to Git for tracking.

4. **Make the First Commit**:
   - Commit the changes to the local repository:
     ```bash
     git commit -m "Initial commit"
     ```

5. **Connect to a Remote Repository (if applicable)**:
   - If you're using a remote repository (e.g., GitHub, GitLab), connect it by adding a remote URL:
     ```bash
     git remote add origin <repository-url>
     ```

6. **Push Changes to the Remote Repository**:
   - Push the local commits to the remote repository:
     ```bash
     git push -u origin master
     ```

This sets up Git for your project and starts version tracking.

---

### 836. **What is `git clone` in GIT?**

`git clone` is a Git command that allows you to create a **local copy of a remote repository**. When you clone a repository, you are copying the entire repository (including its history, branches, and files) from a remote location to your local machine.

Usage:

```bash
git clone <repository-url>
```

For example, if you want to clone a GitHub repository:

```bash
git clone https://github.com/username/repository.git
```

This command performs the following actions:
- Creates a new directory with the name of the repository.
- Copies the repository data (including history and branches) from the remote repository into the local directory.
- Automatically creates a remote reference called `origin` that points to the cloned repository’s URL.

### 837. **How will you create a repository in GIT?**

To create a Git repository, follow these steps:

1. **Initialize a new repository** in an existing project directory:
   - Navigate to your project directory in the terminal and run:
     ```bash
     git init
     ```
   - This will create a new `.git` directory in your project folder, marking it as a Git repository.

2. **Create a remote repository** (if you want to store it online):
   - If using a platform like GitHub, GitLab, or Bitbucket, go to the site and create a new repository (typically through a web interface).
   - Afterward, link your local repository to the remote repository:
     ```bash
     git remote add origin <repository-url>
     ```
   - Then, push your code to the remote repository:
     ```bash
     git push -u origin master
     ```

---

### 838. **What are the different ways to start work in GIT?**

The different ways to start working with Git are:

1. **Create a New Repository**: Use `git init` to initialize a new repository in your local directory if you are starting a new project.

2. **Clone an Existing Repository**: If you want to contribute to an existing project, use the `git clone <repository-url>` command to create a local copy of a remote repository.

3. **Fork and Clone**: If you are working on a project on GitHub or GitLab, you may fork the project and clone your fork to make changes and later submit pull requests.

---

### 839. **GIT is written in which language?**

Git is primarily written in **C**. It also contains scripts written in Perl, Shell, and Python for specific functionalities, but the core of Git is written in C for speed and efficiency.

---

### 840. **What does ‘git pull’ command in GIT do internally?**

Internally, `git pull` performs two steps:

1. **Fetch**: It fetches the latest changes from the remote repository (default is `origin`) and updates your local repository with the new commits. This is equivalent to `git fetch`.
   
2. **Merge**: After fetching the changes, it automatically tries to merge the changes from the remote branch (e.g., `origin/master`) into your current branch (e.g., `master`). If there are conflicts, Git will alert you to resolve them before completing the merge.

---

### 841. **What does ‘git push’ command in GIT do internally?**

Internally, `git push` performs the following actions:

1. **Push**: Git will push the local commits from your current branch to the corresponding remote branch on the remote repository (e.g., `origin/master`).
   
2. **Transfer objects**: Git transfers the objects (commits, trees, blobs) to the remote repository.

3. **Update the remote reference**: Git updates the remote reference of the branch you pushed to, ensuring that the remote repository reflects your changes.

---

### 842. **What is git stash?**

`git stash` is a command that temporarily saves your local modifications (both staged and unstaged changes) so that you can work on something else without committing the changes. It allows you to "clean" your working directory and later apply the stashed changes.

Usage:
- To stash changes:
  ```bash
  git stash
  ```
- To view the list of stashes:
  ```bash
  git stash list
  ```
- To apply a stashed change:
  ```bash
  git stash apply
  ```

---

### 843. **What is the meaning of ‘stage’ in GIT?**

In Git, the **staging area** (also called the **index**) is where you prepare changes before committing them to the repository. When you modify files, these changes are not automatically tracked by Git until you add them to the staging area with the `git add` command. 

For example:
- `git add <file>` stages the changes in the file for the next commit.
- The staging area allows you to decide what changes to commit (not necessarily all the modified files).

---

### 844. **What is the purpose of git config command?**

The `git config` command is used to set Git configuration settings at various levels:

1. **Global configuration**: This applies to the entire user account (typically located in `~/.gitconfig`).
   - Example: `git config --global user.name "John Doe"`
   
2. **Local configuration**: This applies only to the current repository (stored in `.git/config`).
   - Example: `git config user.email "john.doe@example.com"`

3. **System configuration**: Applies to all users on the system (stored in `/etc/gitconfig`).
   - Example: `git config --system core.editor vim`

---

### 845. **How can we see the configuration settings of GIT installation?**

To view the current Git configuration settings, use:

```bash
git config --list
```

This will show the values of all Git configuration settings, including user details, editor preferences, etc.

To see settings for a specific level (local, global, or system), use the following:
- Local: `git config --local --list`
- Global: `git config --global --list`
- System: `git config --system --list`

---

### 846. **How will you write a message with commit command in GIT?**

To write a commit message, use the `git commit` command followed by the `-m` option and the message in quotes:

```bash
git commit -m "Your commit message here"
```

Example:

```bash
git commit -m "Fixed the bug in login form validation"
```

---

### 847. **What is stored inside a commit object in GIT?**

A Git commit object contains:

1. **Commit message**: The message describing the changes in the commit.
2. **Tree object**: A pointer to the directory structure (or tree) of the committed files.
3. **Parent commits**: References to the parent commit(s), establishing the history of the repository.
4. **Author and committer information**: The name and email of the author and the committer, as well as the commit timestamps.
5. **Commit hash (SHA-1)**: A unique identifier for the commit based on its contents.

---

### 848. **How many heads can you create in a GIT repository?**

In Git, **heads** refer to references to the latest commit of a branch. You can create as many heads (branches) as you need in a repository, limited only by practical storage and organizational constraints. Each branch can have its own head, and there's no set limit on the number of heads in a repository.

---

### 849. **Why do we create branches in GIT?**

Branches in Git are used for various purposes, including:

1. **Feature Development**: Create separate branches for each feature or bug fix to work on them independently of the main codebase (typically `master` or `main`).
   
2. **Collaboration**: Allows multiple developers to work on different features or bugs simultaneously without interfering with each other’s code.

3. **Experimentation**: Branches allow you to experiment with new ideas or prototypes without affecting the stable code in the main branch.

4. **Release Management**: Create branches to manage different stages of the project, such as development, testing, and production.

5. **Code Isolation**: Keeps the work isolated until it's ready to be merged into the main branch, minimizing the risk of breaking the main project.

### 850. **What are the different kinds of branches that can be created in GIT?**

In Git, there are several kinds of branches that can be created, based on the purpose they serve:

1. **Master/Main Branch**: This is the default branch where the stable code is usually stored. Many projects use `main` or `master` as the default name for this branch.

2. **Feature Branches**: Used for developing new features or working on specific tasks or fixes. These branches are created off the `main` or `develop` branch and are merged back when the feature is complete.

3. **Release Branches**: Used for preparing a new release version of the software. It allows for bug fixes, documentation generation, and other release-oriented tasks without interrupting the development process.

4. **Hotfix Branches**: Created to fix urgent issues or bugs in the `main` or production branch. These are often merged back into both `main` and `develop` branches.

5. **Develop Branch**: Some projects use a `develop` branch where all feature branches are merged before being merged into the `main` branch. It's used as a staging area for features before they are ready for release.

6. **Experiment/Prototype Branches**: These are temporary branches used for experiments or proof of concept. They may not be merged back into the main codebase.

---

### 851. **How will you create a new branch in GIT?**

To create a new branch in Git, use the `git branch` command followed by the branch name:

```bash
git branch <branch-name>
```

After creating the branch, switch to it using `git checkout`:

```bash
git checkout <branch-name>
```

Alternatively, you can combine the two steps into one with:

```bash
git checkout -b <branch-name>
```

---

### 852. **How will you add a new feature to the main branch?**

To add a new feature to the main branch, follow these steps:

1. **Checkout the main branch**:
   ```bash
   git checkout main
   ```

2. **Pull the latest changes** (to ensure your local branch is up-to-date):
   ```bash
   git pull origin main
   ```

3. **Create a new feature branch**:
   ```bash
   git checkout -b feature/<feature-name>
   ```

4. **Develop the new feature** by making changes to your code.

5. **Stage and commit your changes**:
   ```bash
   git add .
   git commit -m "Added new feature"
   ```

6. **Push the feature branch to the remote**:
   ```bash
   git push origin feature/<feature-name>
   ```

7. **Create a pull request** (PR) to merge the feature branch into the main branch.

---

### 853. **What is a pull request in GIT?**

A **pull request** (PR) is a request made by a developer to merge their changes (typically from a feature branch) into another branch, usually the main or development branch. It allows for code review, discussion, and automated checks (like CI/CD pipelines) before the merge happens.

PRs are typically used in Git hosting services like GitHub, GitLab, or Bitbucket.

---

### 854. **What is merge conflict in GIT?**

A **merge conflict** occurs when Git cannot automatically merge changes from two branches due to conflicting modifications in the same part of a file. This usually happens when two branches modify the same line of code, or if one branch deletes a file while another branch modifies it.

---

### 855. **How can we resolve a merge conflict in GIT?**

To resolve a merge conflict in Git:

1. **Identify the conflicting files**: Git will notify you of the conflict during the merge process.

2. **Open the conflicting files**: In the files, you'll see conflict markers like:
   ```bash
   <<<<<<< HEAD
   <your changes>
   =======
   <changes from the other branch>
   >>>>>>> branch-name
   ```

3. **Resolve the conflict**: Manually edit the file to select or combine the changes. Remove the conflict markers.

4. **Stage the resolved files**:
   ```bash
   git add <file-name>
   ```

5. **Commit the merge**:
   ```bash
   git commit
   ```

6. Optionally, push the changes to the remote repository:
   ```bash
   git push
   ```

---

### 856. **What command will you use to delete a branch?**

To delete a local branch, use:

```bash
git branch -d <branch-name>
```

To force delete a branch (even if it has unmerged changes), use:

```bash
git branch -D <branch-name>
```

To delete a remote branch, use:

```bash
git push origin --delete <branch-name>
```

---

### 857. **What command will you use to delete a branch that has unmerged changes?**

To delete a branch with unmerged changes, use the force delete option:

```bash
git branch -D <branch-name>
```

This will delete the branch even if it contains unmerged changes.

---

### 858. **What is the alternative command to merging in GIT?**

An alternative to merging is **rebasing**. Instead of merging two branches, rebasing applies the changes from one branch onto another, which results in a linear history.

Example:

```bash
git rebase <branch-name>
```

---

### 859. **What is Rebasing in GIT?**

**Rebasing** in Git is the process of taking all the changes from one branch and replaying them on top of another branch. It allows you to keep a clean, linear project history by avoiding merge commits.

For example, if you're working on a feature branch and want to apply its changes on top of the latest changes in the `main` branch, you can rebase your branch on `main`.

```bash
git checkout feature-branch
git rebase main
```

---

### 860. **What is the ‘Golden Rule of Rebasing’ in GIT?**

The **Golden Rule of Rebasing** is: **Never rebase commits that you have already pushed to a shared repository.**

Rebasing rewrites the commit history, and rebasing already shared commits can cause problems for other collaborators who have based their work on the commits you're rewriting.

---

### 861. **Why do we use Interactive Rebasing in place of Auto Rebasing?**

**Interactive rebasing** allows you to edit, reorder, squash, or remove commits, providing more control over your commit history. It is particularly useful when you want to:

- **Clean up commits** before pushing to a shared repository.
- **Squash** multiple commits into one to simplify history.
- **Edit commit messages**.
- **Reorder commits** for clarity or to avoid conflicts.

To perform an interactive rebase:

```bash
git rebase -i <commit-hash>
```

---

### 862. **What is the command for Rebasing in Git?**

To rebase a branch onto another branch, use:

```bash
git rebase <branch-name>
```

To perform an interactive rebase:

```bash
git rebase -i <commit-hash>
```

---

### 863. **What is the main difference between git clone and git remote?**

- **git clone**: Creates a local copy of a remote repository, along with its entire history.
  ```bash
  git clone <repository-url>
  ```

- **git remote**: Manages the set of repositories that you track in your local repository. It allows you to view, add, or remove remote repositories.
  ```bash
  git remote -v  # View remote repositories
  git remote add origin <url>  # Add a new remote repository
  ```

---

### 864. **What is GIT version control?**

**Git** is a distributed version control system that tracks changes in source code during software development. It allows multiple developers to work on a project simultaneously, manage version histories, and collaborate on code changes without interfering with each other's work. Git provides features like branching, merging, rebasing, and reverting changes, making it highly efficient and flexible for managing code revisions and history.

Here are the answers to your Git-related questions:

### 865. **What GUI do you use for working on GIT?**

There are several graphical user interfaces (GUIs) available for working with Git. Some popular ones include:

1. **GitHub Desktop**: A simple Git GUI designed for GitHub users.
2. **Sourcetree**: A free Git GUI tool that simplifies version control.
3. **GitKraken**: A Git GUI with an intuitive interface for managing repositories.
4. **Tower**: A powerful Git GUI for macOS and Windows.
5. **SmartGit**: A cross-platform Git GUI with a wide range of features.

---

### 866. **What is the use of git diff command in GIT?**

The `git diff` command is used to show the differences between the working directory and the index (staging area), or between the staging area and the last commit. It allows you to review changes that have been made but not yet committed.

Example:
```bash
git diff
```

---

### 867. **What is git rerere?**

`git rerere` (reuse recorded resolution) is a Git command that helps in automatically resolving merge conflicts by recording the resolutions of conflicts when they occur. If the same conflict happens again in the future, Git will automatically apply the previously recorded resolution.

To enable `git rerere`, use:
```bash
git config --global rerere.enabled true
```

---

### 868. **What are the three most popular versions of the git diff command?**

The three most popular versions of the `git diff` command are:

1. **`git diff`**: Shows the differences between the working directory and the staging area (index).
2. **`git diff --cached`**: Shows the differences between the staging area (index) and the last commit.
3. **`git diff <commit1> <commit2>`**: Shows the differences between two commits.

---

### 869. **What is the use of git status command?**

The `git status` command is used to display the current state of the repository, including:

- Which files are staged for the next commit.
- Which files have been modified but are not staged.
- Which files are untracked (i.e., new files not yet added to the repository).
  
Example:
```bash
git status
```

---

### 870. **What is the main difference between git diff and git status?**

- **`git diff`**: Shows the differences between your working directory and the staging area or between commits. It displays the actual changes (line-by-line) in the files.
- **`git status`**: Provides an overview of the state of the repository, including staged and unstaged changes, untracked files, and branch information. It does not show line-by-line differences but instead gives a high-level summary.

---

### 871. **What is the use of git rm command in GIT?**

The `git rm` command is used to remove files from the working directory and stage the removal for the next commit. This command is useful when you want to delete a file and track that deletion in Git.

Example:
```bash
git rm <file-name>
```

If you want to only remove a file from Git's tracking but keep it in the working directory:
```bash
git rm --cached <file-name>
```

---

### 872. **What is the command to apply a stash?**

To apply a stashed change, use the following command:
```bash
git stash apply
```

This applies the most recent stash. If you want to apply a specific stash, use the stash reference:
```bash
git stash apply stash@{2}
```

To remove the applied stash from the stash list, use `git stash pop` instead:
```bash
git stash pop
```

---

### 873. **Why do we use git log command?**

The `git log` command is used to view the commit history of a repository. It displays detailed information about the commits, such as the commit ID, author, date, and commit message. It is helpful for tracking changes and understanding the history of the repository.

Example:
```bash
git log
```

You can also use various options to customize the log output (e.g., `--oneline`, `--graph`, etc.).

---

### 874. **Why do we need git add command in GIT?**

The `git add` command is used to add changes from the working directory to the staging area in Git. Before committing changes, you need to stage them using `git add`, which tells Git which changes should be included in the next commit.

Example:
```bash
git add <file-name>
```

To stage all changes:
```bash
git add .
```

---

### 875. **Why do we use git reset command?**

The `git reset` command is used to undo changes in your repository. It can be used to:

1. Unstage changes (move changes from the staging area back to the working directory).
2. Reset the current branch to a specific commit (which can be used to discard commits).

Example:
```bash
git reset <commit-hash>  # Reset to a specific commit
```

To unstage files:
```bash
git reset <file-name>
```

---

### 876. **What does a commit object contain?**

A Git commit object contains:

- **A commit hash (SHA-1 identifier)**: A unique identifier for the commit.
- **Author information**: The name and email of the person who created the commit.
- **Commit message**: A description of the changes made in the commit.
- **Parent commit(s)**: References to the commit(s) that came before this commit (except for the initial commit, which has no parent).
- **Changes**: A snapshot of the changes made to the files in the commit.

---

### 877. **How can we convert git log messages to a different format?**

You can convert Git log messages to a different format using the `--pretty` option. For example:

1. **One-line format**:
   ```bash
   git log --pretty=oneline
   ```

2. **Custom format** (e.g., showing commit hash and message):
   ```bash
   git log --pretty=format:"%h %s"
   ```

3. **Graphical format**:
   ```bash
   git log --graph
   ```

You can also combine these options for more advanced formatting.

---

### 878. **What are the programming languages in which git hooks can be written?**

Git hooks are usually written in **any language** that can execute commands in the shell. Commonly, they are written in:

- **Shell scripting (Bash, etc.)**
- **Python**
- **Ruby**
- **Perl**
- **Node.js**
- **Any other language** that can invoke system commands and execute scripts.

---

### 879. **What is a commit message in GIT?**

A **commit message** in Git is a short, descriptive text that accompanies a commit to explain the changes made. It helps collaborators understand what was changed and why. Commit messages should be concise but informative, following conventions like:

1. **A short summary** of the change (50 characters or less).
2. **A detailed explanation** of the change, if necessary (with the body wrapped at 72 characters).

Example commit message:
```bash
git commit -m "Fix bug in user login form"
```

Here are the answers to your GIT-related questions:

### 880. **How GIT protects the code in a repository?**

Git protects the code in a repository in the following ways:

1. **Version Control**: Git maintains a history of all commits, allowing you to track every change made to the code.
2. **Branching**: Git allows you to create branches, enabling you to work on different features or bug fixes without affecting the main codebase.
3. **Hashing**: Each commit in Git is identified by a unique hash (SHA-1), which ensures the integrity of the code. Any change in the commit will result in a different hash, making it easy to detect any corruption or unauthorized modification.
4. **Distributed Nature**: Git is a distributed version control system, so every contributor has a full copy of the repository and its history, ensuring that the code is protected even if one machine fails.

---

### 881. **How GIT provides flexibility in version control?**

Git provides flexibility in version control in the following ways:

1. **Branching and Merging**: Git allows you to create and manage multiple branches, which enables parallel development and experimentation without affecting the main codebase. It also supports easy merging of changes between branches.
2. **Local Operations**: Git operates locally, meaning you can make changes, commit, and view history without being connected to a remote server.
3. **Staging Area**: Git provides a staging area where you can prepare and review changes before committing them, allowing for better control of what is included in a commit.
4. **Distributed Nature**: Git is distributed, meaning each developer has a complete copy of the repository, which allows offline work and reduces dependency on a central server.

---

### 882. **How can we change a commit message in GIT?**

You can change the commit message using the following command:

1. **For the most recent commit**:
   ```bash
   git commit --amend
   ```
   This will open your default editor, allowing you to change the commit message.

2. **For an older commit** (using `rebase`):
   ```bash
   git rebase -i HEAD~n
   ```
   Replace `n` with the number of commits back you want to modify. Then, change `pick` to `reword` for the commit whose message you want to modify, and edit the message in the editor.

---

### 883. **Why is it advisable to create an additional commit instead of amending an existing commit?**

It is advisable to create an additional commit instead of amending an existing commit because:

1. **Preservation of History**: Git is designed to preserve history. Amending commits rewrites history, which could cause confusion or conflicts, especially in a shared repository.
2. **Collaboration**: When working with a team, amending commits can disrupt the work of others who may have already pulled the original commit. Creating a new commit avoids this issue.
3. **Auditability**: New commits keep the changes transparent and provide an audit trail of all modifications.

---

### 884. **What is a bare repository in GIT?**

A **bare repository** is a repository without a working directory. It only contains the `.git` directory and all the repository's history and configuration. Bare repositories are typically used as central repositories for collaboration, where developers push their changes but do not directly work with the files.

Example:
```bash
git clone --bare <repository-url>
```

---

### 885. **How do we put a local repository on GitHub server?**

To put a local repository on GitHub:

1. **Create a repository on GitHub**: Go to GitHub and create a new repository.
2. **Add GitHub as a remote**:
   ```bash
   git remote add origin https://github.com/username/repository.git
   ```
3. **Push your local repository to GitHub**:
   ```bash
   git push -u origin master
   ```

---

### 886. **How will you delete a branch in GIT?**

To delete a local branch:
```bash
git branch -d <branch-name>
```
This will delete the branch only if it has been merged. If you want to delete it without checking if it's merged, use:
```bash
git branch -D <branch-name>
```

To delete a remote branch:
```bash
git push origin --delete <branch-name>
```

---

### 887. **How can we set up a Git repository to run code sanity checks and UAT tests just before a commit?**

To set up sanity checks and UAT tests before a commit, you can use **Git hooks**, specifically the `pre-commit` hook.

1. Go to the `.git/hooks` directory in your repository.
2. Rename or create the `pre-commit` file and make it executable.
3. In the `pre-commit` file, add the script to run your tests, for example:
   ```bash
   #!/bin/sh
   ./run-sanity-checks.sh
   ./run-uat-tests.sh
   ```

This way, every time you attempt to commit, the checks will run before the commit is finalized.

---

### 888. **How can we revert a commit that was pushed earlier and is public now?**

To revert a commit that has already been pushed to a public repository:

1. Use `git revert` to create a new commit that undoes the changes:
   ```bash
   git revert <commit-hash>
   ```
   This will generate a new commit that undoes the changes from the specified commit.

2. Then push the changes:
   ```bash
   git push origin <branch-name>
   ```

This is the safest way to revert a commit on a public branch, as it doesn't alter the commit history.

---

### 889. **In GIT, how will you compress last n commits into a single commit?**

You can use **interactive rebase** to combine multiple commits into a single commit.

1. Start an interactive rebase:
   ```bash
   git rebase -i HEAD~n
   ```
   Replace `n` with the number of commits you want to squash.

2. In the editor, change the word `pick` to `squash` for the commits you want to combine into the first commit.
3. Save and close the editor.
4. Edit the commit message, if necessary, and finish the rebase.

After this, you'll have a single commit that combines the changes from the last `n` commits.

---

### 890. **How will you switch from one branch to a new branch in GIT?**

To switch to an existing branch:
```bash
git checkout <branch-name>
```

To create and switch to a new branch:
```bash
git checkout -b <new-branch-name>
```

---

### 891. **How can we clean unwanted files from our working directory in GIT?**

To clean unwanted files that are not tracked by Git, you can use the `git clean` command:

```bash
git clean -f
```

To remove directories as well:
```bash
git clean -fd
```

You can also preview which files will be deleted with the `-n` option:
```bash
git clean -f -n
```

---

### 892. **What is the purpose of git tag command?**

The `git tag` command is used to mark specific points in the Git history as important, usually for releases or versions. Tags are commonly used to indicate a version of the code that is stable and ready for release.

Example:
```bash
git tag v1.0
```

To push tags to the remote repository:
```bash
git push origin v1.0
```

---

### 893. **What is cherry-pick in GIT?**

`git cherry-pick` is a command that allows you to apply the changes introduced by a specific commit from one branch to another, without merging the entire branch. It's useful when you want to apply a particular fix or change to another branch without bringing in all the other changes.

Example:
```bash
git cherry-pick <commit-hash>
```

---

### 894. **What is shortlog in GIT?**

The `git shortlog` command is used to summarize the commit history by authors. It groups commits by author and displays a summary of the changes each author has made.

Example:
```bash
git shortlog
```

You can use the `-n` option to sort the log by the number of commits:
```bash
git shortlog -n
```


Here are the answers to your GIT-related questions:

### 895. **How can you find the names of files that were changed in a specific commit?**

You can use the `git show` command to display the files changed in a specific commit:

```bash
git show --name-only <commit-hash>
```

This will show the commit details along with a list of files that were changed in that commit.

---

### 896. **How can we attach an automated script to run on the event of a new commit by push command?**

To run an automated script upon a new commit (e.g., after a push), you can use a **post-receive hook** in your Git server repository:

1. On the server, navigate to the `.git/hooks` directory in your repository.
2. Create or edit the `post-receive` hook file.
3. Add your script or commands inside this file.
   Example:
   ```bash
   #!/bin/sh
   ./run-your-script.sh
   ```

Make sure the script has executable permissions:
```bash
chmod +x post-receive
```

This hook will trigger after a commit is received by the remote repository.

---

### 897. **What is the difference between pre-receive, update, and post-receive hooks in GIT?**

1. **pre-receive**: This hook is executed before the commit is accepted by the repository. You can use it to reject the push or check the changes before they are stored in the repository. It's useful for checking the validity of the push.

2. **update**: This hook is invoked for each branch that is updated. It allows you to check or modify references (branches, tags) that are updated as part of the push.

3. **post-receive**: This hook is run after the push has been received and the changes have been successfully stored in the repository. It's commonly used to trigger actions like notifications or deployment scripts.

---

### 898. **Do we have to store Scripts for GIT hooks within the same repository?**

No, Git hook scripts do not need to be stored within the same repository. Typically, hook scripts are stored in the `.git/hooks` directory, which is not part of the version-controlled repository. However, you can store custom scripts in a separate directory within the repository (e.g., `scripts/`) and manually link them to the `.git/hooks` directory if desired.

---

### 899. **How can we determine the commit that is the source of a bug in GIT?**

To find the commit that introduced a bug, you can use the **`git bisect`** command. This command helps you to perform a binary search through your commit history to identify the exact commit that caused the issue:

1. Start the bisecting process:
   ```bash
   git bisect start
   ```
2. Mark the current commit as bad:
   ```bash
   git bisect bad
   ```
3. Mark the last known good commit:
   ```bash
   git bisect good <commit-hash>
   ```
4. Git will now automatically checkout the middle commit for you. Test your code to see if the bug is present.
5. Mark the result (`good` or `bad`) for the middle commit:
   ```bash
   git bisect good
   # or
   git bisect bad
   ```
6. Repeat this process until Git identifies the commit that introduced the bug.

---

### 900. **How can we see differences between two commits in GIT?**

To see the differences between two commits, use the `git diff` command:

```bash
git diff <commit-hash-1> <commit-hash-2>
```

This will show the changes between the two commits.

---

### 901. **What are the different ways to identify a commit in GIT?**

Commits in Git can be identified using:

1. **Commit hash (SHA-1)**: A 40-character alphanumeric string.
   Example:
   ```bash
   git show <commit-hash>
   ```
2. **Branch name**: If a commit is the latest commit on a branch, you can refer to it by the branch name.
   Example:
   ```bash
   git show <branch-name>
   ```
3. **HEAD**: Refers to the latest commit on the current branch.
   Example:
   ```bash
   git show HEAD
   ```
4. **Relative reference**: A relative commit reference like `HEAD~1` (previous commit) or `HEAD~n` (n commits before HEAD).
   Example:
   ```bash
   git show HEAD~2
   ```

---

### 902. **When we run git branch <branchname>, how does GIT know the SHA-1 of the last commit?**

Git stores the SHA-1 of the last commit for each branch in the reference file located at `.git/refs/heads/<branchname>`. When you run `git branch <branchname>`, Git reads the SHA-1 from this file, which corresponds to the latest commit on the specified branch.

---

### 903. **What are the different types of Tags you can create in GIT?**

There are two types of tags in Git:

1. **Lightweight Tag**: A simple reference to a commit (no additional information).
   ```bash
   git tag <tag-name>
   ```

2. **Annotated Tag**: A full object in Git, storing the tag name, email, date, and the message. Annotated tags are recommended for releases.
   ```bash
   git tag -a <tag-name> -m "Tag message"
   ```

---

### 904. **How can we rename a remote repository?**

To rename a remote repository in Git, follow these steps:

1. First, rename the remote repository URL on your Git hosting service (e.g., GitHub, GitLab).
2. Then, update the remote URL in your local repository using the `git remote` command:
   ```bash
   git remote set-url origin <new-repository-URL>
   ```

To verify the remote URL has been updated:
```bash
git remote -v
```

Here are the answers to your GIT-related questions:

### 905. **Some people use git checkout and some use git co for checkout. How is that possible?**

The command `git co` is an alias for `git checkout`, which means it's not a built-in Git command, but a user-defined shortcut. You can create this alias by adding the following configuration to your `.gitconfig` file:

```bash
git config --global alias.co checkout
```

Once this alias is set, `git co` will work as a shortcut for `git checkout`.

---

### 906. **How can we see the last commit on each of our branches in GIT?**

You can use the following command to see the last commit on each branch:

```bash
git branch -v
```

This will show each branch and the latest commit on that branch.

---

### 907. **Is origin a special branch in GIT?**

No, `origin` is not a branch but a **remote name**. When you clone a repository, Git automatically names the default remote repository `origin`. It is a shorthand reference to the remote repository URL, not a special branch. For example:

```bash
git push origin <branch-name>
```

Here, `origin` refers to the remote repository, and `<branch-name>` is the branch you're pushing to.

---

### 908. **How can we configure GIT to not ask for a password every time?**

To configure Git to not ask for a password every time, you can set up **SSH keys** or use **Git Credential Helpers**.

1. **SSH Keys**: Generate an SSH key pair and add the public key to your Git hosting service (e.g., GitHub, GitLab).

   ```bash
   ssh-keygen -t rsa -b 4096 -C "your_email@example.com"
   ```

   Add the SSH public key to your Git service's SSH settings.

2. **Credential Helper**: Use Git's credential helper to store the password securely.

   To enable the credential helper:

   ```bash
   git config --global credential.helper cache
   ```

   This will store your credentials for a short period (by default 15 minutes), or you can use:

   ```bash
   git config --global credential.helper store
   ```

   This will store your credentials permanently in a plain-text file.

---

### 909. **What are the four major protocols used by GIT for data transfer?**

The four major protocols used by Git for data transfer are:

1. **HTTP/HTTPS**: Used for communicating with remote repositories over the web.
2. **SSH (Secure Shell)**: A secure way to communicate with remote repositories, often used with private repositories.
3. **Git protocol**: A custom Git-specific protocol, typically used for pushing and pulling without authentication.
4. **FTP/FTPS**: Rarely used, but FTP or FTPS can be used to push or pull from a Git repository.

---

### 910. **What is GIT protocol?**

The **Git protocol** is a native protocol used by Git for data transfer between the local repository and remote repositories. It is a lightweight, fast, and efficient way to clone and push to Git repositories. It operates over port 9418 and doesn’t require authentication, making it a common choice for public repositories.

Example of using the Git protocol:

```bash
git clone git://github.com/example/repo.git
```

---

### 911. **How can we work on a project where we do not have push access?**

If you do not have push access to a repository, you can still contribute by using **forking**. Here’s how you can work on a project:

1. Fork the repository on GitHub or another Git service.
2. Clone your forked repository locally.
   ```bash
   git clone https://github.com/yourusername/repository.git
   ```
3. Make changes to your fork.
4. Push your changes to your forked repository.
   ```bash
   git push origin <your-branch>
   ```
5. Create a **Pull Request** to propose your changes to the original repository.

---

### 912. **What is git grep?**

`git grep` is a Git command used to search for content within files tracked by Git. It allows you to search for a string or pattern in the repository's history or working directory.

Example:

```bash
git grep "search-term"
```

This will search for the term "search-term" in the current working directory and return all occurrences.

---

### 913. **How can you reorder commits in GIT?**

To reorder commits in Git, you can use **interactive rebase**. Here’s how:

1. Run:
   ```bash
   git rebase -i HEAD~n
   ```

   Where `n` is the number of commits you want to go back.

2. In the interactive rebase screen, change the order of the commits by reordering the lines.
3. Save and close the editor. Git will apply the commits in the new order.

---

### 914. **How will you split a commit into multiple commits?**

To split a commit into multiple commits:

1. Start an interactive rebase:
   ```bash
   git rebase -i HEAD~n
   ```

2. Mark the commit you want to split with `edit` and save.
3. Git will stop at that commit. Now, unstage all changes:
   ```bash
   git reset HEAD^
   ```

4. Stage and commit the changes one by one.
5. Continue the rebase:
   ```bash
   git rebase --continue
   ```

---

### 915. **What is filter-branch in GIT?**

`git filter-branch` is a powerful Git command used to rewrite history. It allows you to apply a filter to your repository’s commit history, which can include tasks like:

- Changing commit messages
- Rewriting author information
- Removing sensitive data

Example:

```bash
git filter-branch --tree-filter 'rm -rf sensitive-file.txt' HEAD
```

---

### 916. **What are the three main trees maintained by GIT?**

Git maintains three main trees:

1. **Working Directory**: This is the directory where you modify files.
2. **Staging Area (Index)**: This area holds changes that are staged for the next commit.
3. **Repository (HEAD)**: This is where your commit history is stored.

---

### 917. **What are the three main steps of working with GIT?**

The three main steps in working with Git are:

1. **Modify**: Edit files in the working directory.
2. **Stage**: Add changes to the staging area using `git add`.
3. **Commit**: Commit the changes to the repository using `git commit`.

---

### 918. **What are ours and theirs merge options in GIT?**

The `ours` and `theirs` merge options in Git are used during a merge conflict to resolve conflicting files:

- **ours**: Keep the changes from the current branch (the branch you’re merging into).
- **theirs**: Keep the changes from the branch you are merging.

Example:

```bash
git checkout --ours <file>   # Keep your changes
git checkout --theirs <file>  # Keep their changes
```

---

### 919. **How can we ignore merge conflicts due to whitespace?**

To ignore whitespace during a merge conflict, use the following option:

```bash
git merge -X ignore-space-change <branch-name>
```

or

```bash
git merge -X ignore-all-space <branch-name>
```

This will prevent whitespace conflicts from being treated as errors.

---

### 920. **What is git blame?**

`git blame` is used to show who modified each line of a file and when. This is useful for tracking the history of a file and understanding who made specific changes.

Example:

```bash
git blame <file-name>
```

---

### 921. **What is a submodule in GIT?**

A **submodule** in Git is a Git repository embedded inside another Git repository. It allows you to keep a repository as a subdirectory of another repository, enabling you to include and manage external repositories within your own.

Example:

```bash
git submodule add <repository-url> <path>
```

To initialize and update a submodule:

```bash
git submodule init
git submodule update
```
