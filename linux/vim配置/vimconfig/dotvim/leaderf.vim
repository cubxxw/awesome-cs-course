" search word under cursor, the pattern is treated as regex, and enter normal
" mode directly
noremap <C-F> :<C-U><C-R>=printf("Leaderf rg -e %s ", expand("<cword>"))<CR>

noremap <C-R> :LeaderfMruCwd<CR>
noremap <leader>ff :LeaderfFunction<CR>

let g:Lf_GtagsAutoGenerate = 1
let g:Lf_RootMarkers = ['.gtags']
let g:Lf_Gtagslabel = 'native-pygments'

noremap <leader>fr :<C-U><C-R>=printf("Leaderf! gtags -r %s --auto-jump", expand("<cword>"))<CR><CR>
noremap <leader>fd :<C-U><C-R>=printf("Leaderf! gtags -d %s --auto-jump", expand("<cword>"))<CR><CR>
noremap <leader>fo :<C-U><C-R>=printf("Leaderf! gtags --recall %s", "")<CR><CR>
noremap <leader>fn :<C-U><C-R>=printf("Leaderf gtags --next %s", "")<CR><CR>
noremap <leader>fp :<C-U><C-R>=printf("Leaderf gtags --previous %s", "")<CR><CR>

highlight Lf_hl_rgHighlight guifg=#000000 guibg=#CCCC66 ctermfg=green ctermbg=185
highlight Lf_hl_match gui=bold guifg=Blue cterm=bold ctermfg=green
highlight Lf_hl_match0 gui=bold guifg=Blue cterm=bold ctermfg=green
highlight Lf_hl_match1 gui=bold guifg=Blue cterm=bold ctermfg=green
highlight Lf_hl_match2 gui=bold guifg=Blue cterm=bold ctermfg=green
highlight Lf_hl_match3 gui=bold guifg=Blue cterm=bold ctermfg=green
highlight Lf_hl_match4 gui=bold guifg=Blue cterm=bold ctermfg=green
highlight Lf_hl_matchRefine  gui=bold guifg=Magenta cterm=bold ctermfg=green
