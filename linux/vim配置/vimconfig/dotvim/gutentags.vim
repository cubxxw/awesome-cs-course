let g:gutentags_add_default_project_roots = 0

" gutentags搜索工程目录的标志，碰到这些文件/目录名就停止向上一级目录递归 "
let g:gutentags_project_root = ['.ctags']

" " 所生成的数据文件的名称 "
let g:gutentags_ctags_tagfile = '.tags'

" " 将自动生成的 tags 文件全部放入 ~/.cache/tags 目录中，避免污染工程目录 "
let s:vim_tags = expand('~/.LfCache/ctags')
let g:gutentags_cache_dir = s:vim_tags

if isdirectory("kernel/") && isdirectory("mm/")
    let g:gutentags_file_list_command = 'find arch/arm* arch/riscv block crypto drivers fs include init ipc kernel lib mm net security sound virt'
endif

" for rt-thread project
if isdirectory("libcpu/") && isdirectory("bsp/")
    let g:gutentags_file_list_command = 'find include libcpu src components examples bsp/rockchip* '
endif

" 检测 ~/.cache/tags 不存在就新建 "
if !isdirectory(s:vim_tags)
    silent! call mkdir(s:vim_tags, 'p')
endif

" 配置 ctags 的参数 "
let g:gutentags_ctags_extra_args = ['--fields=+niazS', '--extra=+q']
let g:gutentags_ctags_extra_args += ['--c++-kinds=+pxI']
let g:gutentags_ctags_extra_args += ['--c-kinds=+px']


